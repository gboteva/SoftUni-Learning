package com.exam.coffeeshop.service;

import com.exam.coffeeshop.model.entity.UserEntity;
import com.exam.coffeeshop.model.service.UserLoginServiceModel;
import com.exam.coffeeshop.model.service.UserRegisterServiceModel;
import com.exam.coffeeshop.model.view.UsersAndCountOrderViewModel;

import java.util.List;

public interface UserService {
    boolean isUsernameFree(String username);

    boolean isEmailFree(String email);

    void registerUser(UserRegisterServiceModel userRegisterServiceModel);

    boolean userExists(String username, String password);

    void login(UserLoginServiceModel userLoginServiceModel);

    void logout();

    UserEntity findUserById(Long id);

    List<UsersAndCountOrderViewModel> getAllUsersWithOrdersCount();
}
