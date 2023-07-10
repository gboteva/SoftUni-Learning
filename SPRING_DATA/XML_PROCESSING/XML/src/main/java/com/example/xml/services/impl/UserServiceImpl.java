package com.example.xml.services.impl;

import com.example.xml.models.DTO.SeedDTO.UserSeedRootDTO;
import com.example.xml.models.DTO.soldProduct.UserRootDTO;
import com.example.xml.models.DTO.soldProduct.UserWithSoldProductDTO;
import com.example.xml.models.entities.User;
import com.example.xml.repositories.UserRepository;
import com.example.xml.services.UserService;
import com.example.xml.util.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidatorUtil validatorUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public long getCount() {
        return userRepository.count();
    }

    @Override
    public void seedData(UserSeedRootDTO userSeedRootDTO) {
        userSeedRootDTO.getUsers()
                .stream().filter(validatorUtil::isValid)
                .map(userSeedDTO -> modelMapper.map(userSeedDTO, User.class))
                .forEach(userRepository::save);
    }

    @Override
    public User getRandomUser() {
        long count = userRepository.count();
        long randomId = ThreadLocalRandom.current().nextLong(1, count + 1);

        return userRepository.findById(randomId).orElse(null);

    }

    @Override
    public UserRootDTO getUserWithSoldProduct() {
        List<User> users = userRepository.findAllWithAtLeastOneSoldProduct();

        UserRootDTO userRootDTO = new UserRootDTO();

        userRootDTO.setUsers(users.stream()
                .map(user -> modelMapper.map(user, UserWithSoldProductDTO.class))
                .collect(Collectors.toList()));

        return userRootDTO;
    }
}
