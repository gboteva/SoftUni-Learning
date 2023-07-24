package bg.softuni.mobilelele.service.Impl;

import bg.softuni.mobilelele.model.entity.UserEntity;
import bg.softuni.mobilelele.model.service.UserLoginServiceModel;
import bg.softuni.mobilelele.repository.UserRepository;
import bg.softuni.mobilelele.service.UserRoleService;
import bg.softuni.mobilelele.service.UserService;
import bg.softuni.mobilelele.user.CurrentUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleService userRoleService;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserRoleService userRoleService, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleService = userRoleService;
        this.currentUser = currentUser;
    }

    @Override
    public void populateUsers() {
        if (userRepository.count() == 0){
            UserEntity admin = new UserEntity();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("test"));
            admin.setFirstName("Admin");
            admin.setLastName("Adminov");
            admin.setActive(true);
            admin.setCreated(LocalDateTime.now());
            admin.setRole(userRoleService.findById(2L));
            userRepository.save(admin);

            UserEntity user = new UserEntity();
            user.setUsername("Pesho");
            user.setPassword(passwordEncoder.encode("test"));
            user.setFirstName("Pesho");
            user.setLastName("Petrov");
            user.setActive(true);
            user.setCreated(LocalDateTime.now());
            user.setRole(userRoleService.findById(1L));

            userRepository.save(user);
        }
    }

    @Override
    public boolean login(UserLoginServiceModel loginServiceModel) {
        Optional<UserEntity> userFromDB = userRepository.findByUsername(loginServiceModel.getUsername());

        if (userFromDB.isEmpty()){
            logout();
            return false;
        } else {
            boolean success = passwordEncoder.matches(
                    loginServiceModel.getRawPassword(),
                    userFromDB.get().getPassword());

            if(success){
                UserEntity loggedInUser = userFromDB.get();
                currentUser.setUsername(loggedInUser.getUsername());
                currentUser.setFirstName(loggedInUser.getFirstName());
                currentUser.setLastName(loggedInUser.getLastName());
                currentUser.setLoggedIn(true);
                currentUser.setRole(loggedInUser.getRole().getName());
            }else {
                logout();
            }

            return success;
        }
    }

    @Override
    public void logout() {
        currentUser.clear();
    }
}
