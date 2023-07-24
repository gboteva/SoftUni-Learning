package bg.softuni.mobilelele.model.entity;

import bg.softuni.mobilelele.model.entity.BaseEntity;
import bg.softuni.mobilelele.model.entity.enums.RoleEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class UserRoleEntity extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private RoleEnum name;

    public RoleEnum getName() {
        return name;
    }

    public void setName(RoleEnum name) {
        this.name = name;
    }
}
