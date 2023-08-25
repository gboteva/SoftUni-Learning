package com.softuni.pathfinder.service;

import com.softuni.pathfinder.model.entity.UserEntity;
import com.softuni.pathfinder.model.serviceModel.UserLoginServiceModel;
import com.softuni.pathfinder.model.serviceModel.UserRegisterServiceModel;
import com.softuni.pathfinder.model.viewModel.UserViewModel;

public interface UserService {
    boolean isUsernameFree(String username);

    void registerUser(UserRegisterServiceModel userRegServiceModel);

    boolean isUserExist(UserLoginServiceModel user);

    UserViewModel findUserViewModelById(Long id);

    UserEntity findUserEntityById(Long id);

    Long findUserIdByUsername(String username);
}
