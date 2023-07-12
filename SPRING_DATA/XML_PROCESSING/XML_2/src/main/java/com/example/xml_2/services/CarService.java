package com.example.xml_2.services;

import com.example.xml_2.models.DTO.carsWithParts.CarWithPartsRootDTO;
import com.example.xml_2.models.DTO.seeds.CarRootSeedDTO;
import com.example.xml_2.models.DTO.toyotaCar.ToyotaCarRootDTO;
import com.example.xml_2.models.entities.Car;

public interface CarService {
    long getCount();

    void seedData(CarRootSeedDTO carRootSeedDTO);

    Car getRandomCar();

    ToyotaCarRootDTO getToyotaCarOrdered();

    CarWithPartsRootDTO getCarWithParts();
}
