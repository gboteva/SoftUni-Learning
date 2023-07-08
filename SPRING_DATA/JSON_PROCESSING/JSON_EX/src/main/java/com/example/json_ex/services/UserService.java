package com.example.json_ex.services;

import com.example.json_ex.models.DTOs.UserSoldDTO;
import com.example.json_ex.models.DTOs.UserWithNamesAgeAndProductsDTO;
import com.example.json_ex.models.entities.User;

import java.io.IOException;
import java.util.List;

public interface UserService {
    void seedData() throws IOException;

    User getRandomUser();

    List<UserSoldDTO> getUsersWithTheirSoldProducts();

    List<UserWithNamesAgeAndProductsDTO> findAllUsersWithAtLeastOneSoldProduct();

}
