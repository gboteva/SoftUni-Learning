package com.example.xml.services;

import com.example.xml.models.DTO.SeedDTO.UserSeedRootDTO;
import com.example.xml.models.DTO.soldProduct.UserRootDTO;
import com.example.xml.models.entities.User;

public interface UserService {
    long getCount();


    void seedData(UserSeedRootDTO userSeedRootDTO);

    User getRandomUser();

    UserRootDTO getUserWithSoldProduct();
}
