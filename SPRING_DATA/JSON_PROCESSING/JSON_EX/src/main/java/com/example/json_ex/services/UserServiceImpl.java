package com.example.json_ex.services;

import com.example.json_ex.models.DTOs.UserSeedDTO;
import com.example.json_ex.models.DTOs.UserSoldDTO;
import com.example.json_ex.models.DTOs.UserWithNamesAgeAndProductsDTO;
import com.example.json_ex.models.entities.User;
import com.example.json_ex.repositories.UserRepository;
import com.example.json_ex.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final static String INPUT_PATH = "src/main/resources/files/users.json";
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    private final Gson gson;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }

    @Override
    public void seedData() throws IOException {

        if (userRepository.count() > 0) {
            return;
        }

        String stringContent = Files.readString(Path.of(INPUT_PATH));

        UserSeedDTO[] userSeedDTOS = gson.fromJson(stringContent, UserSeedDTO[].class);

        Arrays.stream(userSeedDTOS)
                .filter(validationUtil::isValid)
                .map(dto -> modelMapper.map(dto, User.class))
                .forEach(userRepository::save);
    }

    @Override
    public User getRandomUser() {
        long usersCount = userRepository.count();
        long randomId = ThreadLocalRandom.current().nextLong(1, usersCount + 1);

        return userRepository.findById(randomId).orElse(null);

    }

    @Override
    public List<UserSoldDTO> getUsersWithTheirSoldProducts() {
        return userRepository.findAllUsersWithMoreThanOneSoldProduct()
                .stream().map(user -> modelMapper.map(user, UserSoldDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserWithNamesAgeAndProductsDTO> findAllUsersWithAtLeastOneSoldProduct() {
       return userRepository.findAllByCountOfSoldProducts()
                .stream()
                .map(user -> modelMapper.map(user, UserWithNamesAgeAndProductsDTO.class))
                .collect(Collectors.toList());

    }


}
