package com.example.springautomapping.services;

import com.example.springautomapping.models.dto.UserDTO;
import com.example.springautomapping.models.entities.User;

public interface UserService {
    void registerUser(UserDTO userDTO);

    void login(String email, String password);

    void logout();

    User getLoggedInUser();

    void printGamesOfLoggedInUser();

    void saveEntity(User user);

}
