package com.softuni.pathfinder.service.Impl;

import com.softuni.pathfinder.model.entity.RoleEntity;
import com.softuni.pathfinder.model.entity.UserEntity;
import com.softuni.pathfinder.model.entity.enums.LevelEnum;
import com.softuni.pathfinder.model.entity.enums.RoleEnum;
import com.softuni.pathfinder.model.serviceModel.UserLoginServiceModel;
import com.softuni.pathfinder.model.serviceModel.UserRegisterServiceModel;
import com.softuni.pathfinder.model.viewModel.UserViewModel;
import com.softuni.pathfinder.repository.UserRepository;
import com.softuni.pathfinder.service.RoleService;
import com.softuni.pathfinder.service.UserService;
import com.softuni.pathfinder.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleService roleService;
    private final CurrentUser currentUser;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RoleService roleService, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
        this.currentUser = currentUser;
    }

    @Override
    public boolean isUsernameFree(String username) {
       return !userRepository.existsByUsername(username);
    }

    @Override
    public void registerUser(UserRegisterServiceModel userRegServiceModel) {
        UserEntity user = modelMapper.map(userRegServiceModel, UserEntity.class);

        user.setLevel(LevelEnum.BEGINNER);
        user.setRole(Set.of(roleService.getUserRole()));

        userRepository.save(user);
    }

    @Override
    public boolean isUserExist(UserLoginServiceModel user) {
        return userRepository.existsByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public void login(UserLoginServiceModel user) {
        UserEntity userEntity = userRepository.findByUsername(user.getUsername());
        currentUser.setId(userEntity.getId());

        Optional<RoleEntity> role = userEntity.getRole().stream().findFirst();

        if (role.isPresent() && role.get().getRole().equals(RoleEnum.ADMIN)){
            currentUser.setIsAdmin(true);
        }
    }

    @Override
    public void logout() {
        currentUser.setId(null);
        currentUser.setIsAdmin(false);
    }

    @Override
    public UserViewModel findById(Long id) {

        Optional<UserEntity> userEntity = userRepository.findById(id);

         return   modelMapper.map(userEntity.get(), UserViewModel.class);

    }
}
