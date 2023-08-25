package bg.softuni.mobilelele.user;

import bg.softuni.mobilelele.model.entity.enums.RoleEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser {
    private boolean isLoggedIn;
    private String username;
    private String firstName;
    private String lastName;

    private RoleEnum role;

    public boolean isAdmin(){
       return role == RoleEnum.ADMIN;
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

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public void clear(){
        setLoggedIn(false);
        setUsername(null);
        setFirstName(null);
        setLastName(null);
        setRole(null);
    }
}
