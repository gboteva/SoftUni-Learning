package com.softuni.pathfinder.service.Impl;

import com.softuni.pathfinder.model.entity.UserEntity;
import com.softuni.pathfinder.model.entity.enums.LevelEnum;
import com.softuni.pathfinder.model.serviceModel.UserLoginServiceModel;
import com.softuni.pathfinder.model.serviceModel.UserRegisterServiceModel;
import com.softuni.pathfinder.model.viewModel.UserViewModel;
import com.softuni.pathfinder.repository.UserRepository;
import com.softuni.pathfinder.service.RoleService;
import com.softuni.pathfinder.service.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean isUsernameFree(String username) {
       return !userRepository.existsByUsername(username);
    }

    @Override
    public void registerUser(UserRegisterServiceModel userRegServiceModel) {
        UserEntity user = modelMapper.map(userRegServiceModel, UserEntity.class);

        user.setPassword(passwordEncoder.encode(userRegServiceModel.getPassword()));

        user.setLevel(LevelEnum.BEGINNER);
        user.setRole(Set.of(roleService.getUserRole()));

        userRepository.save(user);
    }

    @Override
    public boolean isUserExist(UserLoginServiceModel user) {
        return userRepository.existsByUsernameAndPassword(user.getUsername(), user.getPassword());
    }


    @Override
    public UserViewModel findUserViewModelById(Long id) {

        Optional<UserEntity> userEntity = userRepository.findById(id);

         return   modelMapper.map(userEntity.get(), UserViewModel.class);

    }

    @Override
    public UserEntity findUserEntityById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public Long findUserIdByUsername(String username) {
       return userRepository.findByUsername(username).getId();
    }
}
