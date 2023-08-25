package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.model.entity.UserEntity;
import bg.softuni.mobilelele.model.service.UserLoginServiceModel;
import bg.softuni.mobilelele.model.service.UserRegisterServiceModel;

public interface UserService {

    void populateUsers();

    boolean login(UserLoginServiceModel loginServiceModel);

    void logout();

    void registerAndLoginUser(UserRegisterServiceModel registerServiceModel);

    boolean isUsernameFree(String username);

    UserEntity findById(long id);
}
