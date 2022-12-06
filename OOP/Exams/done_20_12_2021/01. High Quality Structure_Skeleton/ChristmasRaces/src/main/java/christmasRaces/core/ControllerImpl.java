package christmasRaces.core;

import christmasRaces.core.interfaces.Controller;
import christmasRaces.entities.cars.Car;
import christmasRaces.entities.cars.MuscleCar;
import christmasRaces.entities.cars.SportsCar;
import christmasRaces.entities.drivers.Driver;
import christmasRaces.entities.drivers.DriverImpl;
import christmasRaces.entities.races.Race;
import christmasRaces.entities.races.RaceImpl;
import christmasRaces.repositories.interfaces.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static christmasRaces.common.ExceptionMessages.*;
import static christmasRaces.common.OutputMessages.*;

public class ControllerImpl implements Controller {
    private Repository<Driver> driverRepository;
    private Repository<Car> carRepository;
    private Repository<Race> raceRepository;

    public ControllerImpl(Repository<Driver> driverRepository, Repository<Car> carRepository, Repository<Race> raceRepository) {
        this.driverRepository = driverRepository;
        this.carRepository = carRepository;
        this.raceRepository = raceRepository;
    }

    @Override
    public String createDriver(String driver) {
        if (driverRepository.getByName(driver) != null) {
            throw new IllegalArgumentException(String.format(DRIVER_EXISTS, driver));
        }
        Driver driverToAdd = new DriverImpl(driver);
        driverRepository.add(driverToAdd);
        return String.format(DRIVER_CREATED, driver);
    }


    @Override
    public String createCar(String type, String model, int horsePower) {
        if (carRepository.getByName(model) != null) {
            throw new IllegalArgumentException(String.format(CAR_EXISTS, model));
        }
        Car car;
        if (type.equals("Muscle")) {
            car = new MuscleCar(model, horsePower);
        } else {
            car = new SportsCar(model, horsePower);
        }
        this.carRepository.add(car);
        return String.format(CAR_CREATED, car.getClass().getSimpleName(), model);
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {
        Driver driver = this.driverRepository.getByName(driverName);

        if (driver == null) {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }

        Car car = this.carRepository.getByName(carModel);

        if (car == null) {
            throw new IllegalArgumentException(String.format(CAR_NOT_FOUND, carModel));
        }

        driver.addCar(car);
        return String.format(CAR_ADDED, driverName, carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {
        Race race = this.raceRepository.getByName(raceName);

        if (race == null) {
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }

        Driver driver = this.driverRepository.getByName(driverName);

        if (driver == null) {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }

        race.addDriver(driver);
        return String.format(DRIVER_ADDED, driverName, raceName);

    }

    @Override
    public String startRace(String raceName) {
        Race race = this.raceRepository.getByName(raceName);
        if (race == null){
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }

        int laps = race.getLaps();
        List<Driver> fasterDrivers = race.getDrivers().stream()
                .sorted((a, b) -> {
                    double pointsOfA = a.getCar().calculateRacePoints(laps);
                    double pointsOfB = b.getCar().calculateRacePoints(laps);
                    return Double.compare(pointsOfB, pointsOfA);
                }).limit(3)
                .collect(Collectors.toList());

        if (fasterDrivers.size() < 3){
            throw new IllegalArgumentException(String.format(RACE_INVALID,raceName,3));
        }

        StringBuilder builder = new StringBuilder();
        builder.append(String.format(DRIVER_FIRST_POSITION, fasterDrivers.get(0).getName(), raceName)).append(System.lineSeparator());
        fasterDrivers.get(0).winRace();
        builder.append(String.format(DRIVER_SECOND_POSITION, fasterDrivers.get(1).getName(), raceName)).append(System.lineSeparator());
        builder.append(String.format(DRIVER_THIRD_POSITION, fasterDrivers.get(2).getName(), raceName));

        return builder.toString().trim();

    }

    @Override
    public String createRace(String name, int laps) {
        if (this.raceRepository.getByName(name) != null) {
            throw new IllegalArgumentException(String.format(RACE_EXISTS, name));
        }
        Race race = new RaceImpl(name, laps);
        this.raceRepository.add(race);

        return String.format(RACE_CREATED, name);
    }
}
