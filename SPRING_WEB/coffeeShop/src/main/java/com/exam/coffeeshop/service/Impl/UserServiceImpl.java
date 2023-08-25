package com.exam.coffeeshop.service.Impl;

import com.exam.coffeeshop.model.entity.UserEntity;
import com.exam.coffeeshop.model.service.UserLoginServiceModel;
import com.exam.coffeeshop.model.service.UserRegisterServiceModel;
import com.exam.coffeeshop.model.view.UsersAndCountOrderViewModel;
import com.exam.coffeeshop.repository.UserRepository;
import com.exam.coffeeshop.service.UserService;
import com.exam.coffeeshop.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @Override
    public boolean isUsernameFree(String username) {
        return !userRepository.existsByUsername(username);

    }

    @Override
    public boolean isEmailFree(String email) {
        return !userRepository.existsByEmail(email);
    }

    @Override
    public void registerUser(UserRegisterServiceModel userRegisterServiceModel) {
        userRepository.save(modelMapper.map(userRegisterServiceModel, UserEntity.class));
    }

    @Override
    public boolean userExists(String username, String password) {
        return userRepository.existsByUsernameAndPassword(username, password);
    }

    @Override
    public void login(UserLoginServiceModel userLoginServiceModel) {
        UserEntity user = userRepository.findByUsernameAndPassword(userLoginServiceModel.getUsername(),
                userLoginServiceModel.getPassword());

        currentUser.setId(user.getId());
    }

    @Override
    public void logout() {
        currentUser.setId(null);
    }

    @Override
    public UserEntity findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<UsersAndCountOrderViewModel> getAllUsersWithOrdersCount() {

       List<UserEntity> users = userRepository.findAllUsersAndCountOfTheirOrders();
      return users.stream()
               .map(userEntity -> {
                   UsersAndCountOrderViewModel usersAndCountOrderViewModel = new UsersAndCountOrderViewModel();
                   usersAndCountOrderViewModel.setUsername(userEntity.getUsername());
                   usersAndCountOrderViewModel.setOrdersCount(userEntity.getOrders().size());
                   return  usersAndCountOrderViewModel;
               }).collect(Collectors.toList());
    }
}
