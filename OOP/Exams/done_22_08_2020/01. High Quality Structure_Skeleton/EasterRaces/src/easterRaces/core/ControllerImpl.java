package easterRaces.core;

import easterRaces.core.interfaces.Controller;
import easterRaces.entities.cars.Car;
import easterRaces.entities.cars.MuscleCar;
import easterRaces.entities.cars.SportsCar;
import easterRaces.entities.drivers.Driver;
import easterRaces.entities.drivers.DriverImpl;
import easterRaces.entities.racers.Race;
import easterRaces.entities.racers.RaceImpl;
import easterRaces.repositories.interfaces.Repository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static easterRaces.common.ExceptionMessages.*;
import static easterRaces.common.OutputMessages.*;

public class ControllerImpl implements Controller {

    private Repository<Driver> driversRepo;
    private Repository<Car> carsRepo;
    private Repository<Race> raceRepo;

    public ControllerImpl(Repository<Driver> driversRepo, Repository<Car> carsRepo, Repository<Race> raceRepo) {
        this.driversRepo = driversRepo;
        this.carsRepo = carsRepo;
        this.raceRepo = raceRepo;
    }

    @Override
    public String createDriver(String driverName) {

        if (driversRepo.getByName(driverName) != null){
            throw new IllegalArgumentException(String.format(DRIVER_EXISTS, driverName));
        }

        Driver driverToAdd = new DriverImpl(driverName);

        this.driversRepo.add(driverToAdd);

        return String.format(DRIVER_CREATED, driverName);
    }

    @Override
    public String createCar(String type, String model, int horsePower) {

        if (carsRepo.getByName(model) != null){
            throw new IllegalArgumentException(String.format(CAR_EXISTS, model));
        }

        Car car = null;

        if ("Muscle".equals(type)){
            car = new MuscleCar(model, horsePower);
        }else if ("Sports".equals(type)){
            car = new SportsCar(model, horsePower);
        }

        this.carsRepo.add(car);

        return String.format(CAR_CREATED, car.getClass().getSimpleName(), model);
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {

        Driver driver = driversRepo.getByName(driverName);

        if (driver == null){
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }

        Car car = carsRepo.getByName(carModel);

        if (car == null){
            throw new IllegalArgumentException(String.format(CAR_NOT_FOUND, carModel));
        }

        driver.addCar(car);
        return String.format(CAR_ADDED, driverName, carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {
        Race race = raceRepo.getByName(raceName);

        if (race == null){
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }

        Driver driver = driversRepo.getByName(driverName);

        if (driver == null){
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }

        race.addDriver(driver);
        return String.format(DRIVER_ADDED, driverName, raceName);

    }

    @Override
    public String startRace(String raceName) {
        Race race = raceRepo.getByName(raceName);

        if (race == null){
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }

        List<Driver> drivers = this.driversRepo.getAll().stream()
                .filter(Driver::getCanParticipate)
                .collect(Collectors.toList());

        if (drivers.size() < 3){
            throw new IllegalArgumentException(String.format(RACE_INVALID, raceName, 3));
        }


        Comparator<Driver> comparator = ((d1, d2) -> Double.compare(d2.getCar().calculateRacePoints(race.getLaps()), d1.getCar().calculateRacePoints(race.getLaps()))) ;

        List<Driver> sorted = drivers.stream()
                .sorted(comparator)
                .limit(3)
                .collect(Collectors.toList());

        StringBuilder builder = new StringBuilder();
        builder.append("Driver ").append(sorted.get(0).getName())
                .append(" wins ").append(raceName).append(" race.")
                .append(System.lineSeparator());

        builder.append("Driver ").append(sorted.get(1).getName())
                .append(" is second in ").append(raceName).append(" race.")
                .append(System.lineSeparator());

        builder.append("Driver ").append(sorted.get(2).getName())
                .append(" is third in ").append(raceName).append(" race.");

        return builder.toString().trim();
    }

    @Override
    public String createRace(String name, int laps) {

        if (raceRepo.getByName(name) != null){
            throw new IllegalArgumentException(String.format(RACE_EXISTS, name));
        }

        Race race = new RaceImpl(name, laps);

        this.raceRepo.add(race);

        return String.format(RACE_CREATED, name);
    }
}
