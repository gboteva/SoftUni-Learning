package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.model.entity.UserRoleEntity;

public interface UserRoleService {
    void populateRoles();
    UserRoleEntity findById(Long id);
}
