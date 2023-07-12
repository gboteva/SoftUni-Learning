package com.example.xml_2.services;

import com.example.xml_2.models.DTO.carsWithParts.CarWithParts;
import com.example.xml_2.models.DTO.carsWithParts.CarWithPartsRootDTO;
import com.example.xml_2.models.DTO.seeds.CarRootSeedDTO;
import com.example.xml_2.models.DTO.toyotaCar.ToyotaCarDTO;
import com.example.xml_2.models.DTO.toyotaCar.ToyotaCarRootDTO;
import com.example.xml_2.models.entities.Car;
import com.example.xml_2.repositories.CarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;

    private final PartService partService;

    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper, PartService partService) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.partService = partService;
    }

    @Override
    public long getCount() {
        return carRepository.count();
    }

    @Override
    public void seedData(CarRootSeedDTO carRootSeedDTO) {
       carRootSeedDTO.getCars()
                .stream().map(carSeedDTO -> {
                    Car car = modelMapper.map(carSeedDTO, Car.class);
                    car.setParts(partService.getRandomParts());

                    return car;
                })
                .forEach(carRepository::save);
    }

    @Override
    public Car getRandomCar() {
        long count = carRepository.count();

        long randomId = ThreadLocalRandom.current().nextLong(1, count +1);

        return carRepository.findById(randomId).orElse(null);

    }

    @Override
    public ToyotaCarRootDTO getToyotaCarOrdered() {
        List<Car> toyotaCars = carRepository.findAllByMakeOrderByModelAndTravelledDistance("Toyota");

        List<ToyotaCarDTO> toyotaList = toyotaCars.stream()
                .map(car -> modelMapper.map(car, ToyotaCarDTO.class))
                .collect(Collectors.toList());

        ToyotaCarRootDTO rootDto = new ToyotaCarRootDTO();
        rootDto.setCars(toyotaList);

        return rootDto;

    }

    @Override
    public CarWithPartsRootDTO getCarWithParts() {

        List<Car> cars = carRepository.findAll();

        List<CarWithParts> carsWithPartsDTOs = cars.stream()
                .map(car -> modelMapper.map(car, CarWithParts.class))
                .collect(Collectors.toList());

        CarWithPartsRootDTO dto = new CarWithPartsRootDTO();
        dto.setCars(carsWithPartsDTOs);

        return  dto;
    }
}
