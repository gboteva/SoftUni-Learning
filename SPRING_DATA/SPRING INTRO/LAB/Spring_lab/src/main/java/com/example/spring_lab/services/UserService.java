package com.example.spring_lab.services;

import com.example.spring_lab.models.User;

public interface UserService {
    void registerUser(User user);
    User getUserByUsername(String username);
}
