package bg.softuni.mobilelele.model.binding;


import bg.softuni.mobilelele.model.validator.UniqueUsername;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RegisterBindingModel {
    @NotNull
    @Size(min = 4, max = 20, message = "Invalid first name")
    private String firstName;

    @NotNull
    @Size(min = 4, max = 20, message = "Invalid last name")
    private String lastName;
    @UniqueUsername
    @Size(min = 2, max = 20, message = "Invalid username")
    private String username;
    @NotNull
    @Size(min = 4, max = 20, message = "Invalid password")
    private String password;
    private String role;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
