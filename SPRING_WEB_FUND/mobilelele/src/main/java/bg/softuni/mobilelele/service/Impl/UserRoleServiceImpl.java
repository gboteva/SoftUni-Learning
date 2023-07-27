package bg.softuni.mobilelele.service.Impl;

import bg.softuni.mobilelele.model.entity.UserRoleEntity;
import bg.softuni.mobilelele.model.entity.enums.RoleEnum;
import bg.softuni.mobilelele.repository.UserRoleRepository;
import bg.softuni.mobilelele.service.UserRoleService;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void populateRoles() {
        if (userRoleRepository.count() == 0){
            UserRoleEntity userRoleEntity = new UserRoleEntity();
            userRoleEntity.setName(RoleEnum.USER);
            UserRoleEntity userRoleEntity2 = new UserRoleEntity();
            userRoleEntity2.setName(RoleEnum.ADMIN);

            userRoleRepository.save(userRoleEntity);
            userRoleRepository.save(userRoleEntity2);
        }
    }

    @Override
    public UserRoleEntity findById(Long id) {
        return userRoleRepository.findById(id).orElse(null);
    }

    @Override
    public UserRoleEntity findByRoleName(String name) {
        RoleEnum role = name.equalsIgnoreCase("USER")
                ? RoleEnum.USER
                : RoleEnum.ADMIN;

        return userRoleRepository.findByName(role);
    }
}
