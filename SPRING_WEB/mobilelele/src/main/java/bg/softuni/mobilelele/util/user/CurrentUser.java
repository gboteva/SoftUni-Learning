package bg.softuni.mobilelele.util.user;

import bg.softuni.mobilelele.model.entity.UserRoleEntity;
import bg.softuni.mobilelele.model.entity.enums.RoleEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
@SessionScope
public class CurrentUser {
    private boolean isLoggedIn;
    private String username;
    private String firstName;
    private String lastName;

    private Set<UserRoleEntity> roles = new HashSet<>();

    public boolean isAdmin(){
        Optional<UserRoleEntity> adminRole = roles.stream()
                .filter(role -> role.getName().equals(RoleEnum.ADMIN))
                .findFirst();

        if (adminRole.isEmpty()){
            return false;
        }

        return true;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<UserRoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRoleEntity> roles) {
        this.roles = roles;
    }

    public void clear(){
        setLoggedIn(false);
        setUsername(null);
        setFirstName(null);
        setLastName(null);
        setRoles(new HashSet<>());
    }
}
