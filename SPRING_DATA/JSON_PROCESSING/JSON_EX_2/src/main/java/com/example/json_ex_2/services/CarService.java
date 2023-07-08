package com.example.json_ex_2.services;

import com.example.json_ex_2.models.DTO.ToyotaCarDTO;
import com.example.json_ex_2.models.entities.Car;

import java.io.IOException;
import java.util.List;

public interface CarService {
    void seedData() throws IOException;

    Car getRandomCar();

    String getAllToyotaCarOrderByModelAndTravelledDistance();

    String getAllCarWithListOfParts();

}
