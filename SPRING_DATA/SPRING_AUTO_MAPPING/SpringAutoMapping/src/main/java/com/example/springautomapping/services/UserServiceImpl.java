package com.example.springautomapping.services;

import com.example.springautomapping.models.dto.GameViewTitleDTO;
import com.example.springautomapping.models.dto.UserDTO;
import com.example.springautomapping.models.entities.Game;
import com.example.springautomapping.models.entities.ShoppingCart;
import com.example.springautomapping.models.entities.User;
import com.example.springautomapping.repositories.UserRepository;
import com.example.springautomapping.utils.ValidationUtil;
import jakarta.annotation.PostConstruct;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    //private GameService gameService;
    private User loggedInUser;



    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        loggedInUser = null;

        //setGameService(gameService);

    }

//    @Autowired
//    public void setGameService(GameService gameService){
//        //When we have a circular dependency, it’s likely we have a design problem and that
//        // the responsibilities are not well separated. We should try to redesign the components
//        // properly so that their hierarchy is well designed and there is no need for circular dependencies.
//        // Use @Lazy -
//        //Use Setter/Field Injection -> The preferred method is using setter injections.
//        this.gameService = gameService;
//    }
    @Override
    public void registerUser(UserDTO userDTO) {
        //Confirm Password – must match the provided password
        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())){
            throw new IllegalArgumentException("Passwords doesn't match");
        }

        // violations after validation
        Set<ConstraintViolation<UserDTO>> violation = validationUtil.getViolation(userDTO);
        if (!violation.isEmpty()){
            violation.stream().map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return;
        }

        //Email must contains "."
        if (!userDTO.getEmail().contains(".")){
            throw new IllegalArgumentException("Incorrect Email");
        }

        User user = userRepository.findByEmail(userDTO.getEmail());

        //email must ne unique
        if (user != null){
            throw new IllegalStateException("Email already exist!");
        }


        User mappedUser = modelMapper.map(userDTO, User.class);

        userRepository.save(mappedUser);
        System.out.printf("%s was registered%n",mappedUser.getFullName());

    }

    @Override
    public void login(String email, String password) {
        User user = userRepository.findByEmailAndPassword(email, password);

        if (user == null){
            throw new IllegalArgumentException("Incorrect username / password");
        }

        loggedInUser = user;
        System.out.println("Successfully logged in " + user.getFullName());
    }

    @Override
    public void logout() {
        if (loggedInUser == null){
            throw new IllegalStateException("Cannot log out. No user was logged in.");
        }

        String username = loggedInUser.getFullName();

        loggedInUser = null;

        System.out.printf("User %s successfully logged out%n", username);
    }

    @Override
    public User getLoggedInUser() {
        return this.loggedInUser;
    }

    @Override
    public void printGamesOfLoggedInUser() {

        User loggedInUser = getLoggedInUser();
        if (loggedInUser == null){
            throw new IllegalStateException("Please login!");
        }

        for (Game game : loggedInUser.getGames()) {
            System.out.println(modelMapper.map(game, GameViewTitleDTO.class));
        }

    }

    @Override
    public void saveEntity(User user) {
        userRepository.save(user);
    }


}
