package bg.softuni.mobilelele.service.Impl;

import bg.softuni.mobilelele.model.entity.UserEntity;
import bg.softuni.mobilelele.model.service.UserLoginServiceModel;
import bg.softuni.mobilelele.model.service.UserRegisterServiceModel;
import bg.softuni.mobilelele.repository.UserRepository;
import bg.softuni.mobilelele.service.UserRoleService;
import bg.softuni.mobilelele.service.UserService;
import bg.softuni.mobilelele.util.user.CurrentUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

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
            admin.setRoles(Set.of(userRoleService.findById(2L), userRoleService.findById(1L)));
            userRepository.save(admin);

            UserEntity user = new UserEntity();
            user.setUsername("Pesho");
            user.setPassword(passwordEncoder.encode("test"));
            user.setFirstName("Pesho");
            user.setLastName("Petrov");
            user.setActive(true);
            user.setCreated(LocalDateTime.now());
            user.setRoles(Set.of(userRoleService.findById(1L)));

            userRepository.save(user);


            UserEntity galka = new UserEntity();
            galka.setUsername("galkab");
            galka.setPassword(passwordEncoder.encode("test"));
            galka.setFirstName("Galka");
            galka.setLastName("Boteva");
            galka.setActive(true);
            galka.setCreated(LocalDateTime.now());
            galka.setRoles(Set.of(userRoleService.findById(1L)));
            userRepository.save(galka);
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
                loginUser(loggedInUser);
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

    @Override
    public void registerAndLoginUser(UserRegisterServiceModel registerServiceModel) {
        UserEntity newUser = new UserEntity();
        newUser.setUsername(registerServiceModel.getUsername());
        newUser.setPassword(passwordEncoder.encode(registerServiceModel.getPassword()));
        newUser.setFirstName(registerServiceModel.getFirstName());
        newUser.setLastName(registerServiceModel.getLastName());
        newUser.setActive(true);
        newUser.setCreated(LocalDateTime.now());
        newUser.setRoles(Set.of(userRoleService.findByRoleName(registerServiceModel.getRole())));

        newUser = userRepository.save(newUser);

        loginUser(newUser);

    }

    @Override
    public boolean isUsernameFree(String username) {
        return !userRepository.existsByUsernameIgnoreCase(username);
    }

    @Override
    public UserEntity findById(long id) {
      return userRepository.findById(id).orElse(null);
    }

    private void loginUser(UserEntity user){
        currentUser.setUsername(user.getUsername());
        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        currentUser.setLoggedIn(true);
        currentUser.setRoles(user.getRoles());
    }
}
