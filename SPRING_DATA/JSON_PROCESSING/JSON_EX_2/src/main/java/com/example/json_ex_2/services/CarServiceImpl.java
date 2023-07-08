package com.example.json_ex_2.services;

import com.example.json_ex_2.models.DTO.CarSeedDTO;
import com.example.json_ex_2.models.DTO.CarWithMakeModelAndDistanceDTO;
import com.example.json_ex_2.models.DTO.PartWithNameAndPriceDTO;
import com.example.json_ex_2.models.DTO.ToyotaCarDTO;
import com.example.json_ex_2.models.entities.Car;
import com.example.json_ex_2.repositories.CarRepository;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService{

    public static final String INPUT_PATH = "src/main/resources/input/cars.json";
    private final CarRepository carRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;

    private final PartService partService;

    public CarServiceImpl(CarRepository carRepository, Gson gson, ModelMapper modelMapper, PartService partService) {
        this.carRepository = carRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.partService = partService;
    }

    @Override
    public void seedData() throws IOException {
        if (carRepository.count() > 0){
            return;
        }

        String jsonContent = Files.readString(Path.of(INPUT_PATH));

        Arrays.stream(gson.fromJson(jsonContent, CarSeedDTO[].class))
                .map(dto -> {
                    Car car = modelMapper.map(dto, Car.class);
                    car.setParts(partService.getRandomParts());
                    return car;
                })
                .forEach(carRepository::save);

    }

    @Override
    public Car getRandomCar() {
        long carRepoCount = carRepository.count();

        long randomId = ThreadLocalRandom.current().nextLong(1, carRepoCount + 1);

        return carRepository.findById(randomId).orElse(null);

    }

    @Override
    public String getAllToyotaCarOrderByModelAndTravelledDistance() {
        List<Car> toyota = carRepository.findAllByMakeOrderByModelAscTravelledDistanceDesc("Toyota");

        List<ToyotaCarDTO> dtos = toyota.stream().map(car -> modelMapper.map(car, ToyotaCarDTO.class))
                .collect(Collectors.toList());

        return gson.toJson(dtos);
    }

    @Override
    public String getAllCarWithListOfParts() {
        List<Car> allCars = carRepository.findAll();

        List<CarWithMakeModelAndDistanceDTO> carsDTO = allCars.stream()
                .map(car ->
                     modelMapper.map(car, CarWithMakeModelAndDistanceDTO.class))
                .collect(Collectors.toList());

        return gson.toJson(carsDTO);
    }

}
