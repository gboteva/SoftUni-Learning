package com.example.spring_lab.services;

import com.example.spring_lab.models.User;
import com.example.spring_lab.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void registerUser(User user) {
        User found = userRepository.getByUsername(user.getUsername());

        if (found != null){
            System.out.println("User already exists");
            return;
        }

        userRepository.save(user);
    }

    @Override
    public User getUserByUsername(String username){

       User found = userRepository.getByUsername(username);
       if (found== null){
           throw new NullPointerException("username doesn't exist in database");
       }
       return found;
    }
}
