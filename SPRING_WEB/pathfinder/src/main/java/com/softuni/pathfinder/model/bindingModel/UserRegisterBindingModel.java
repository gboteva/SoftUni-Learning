package com.softuni.pathfinder.model.bindingModel;

import com.softuni.pathfinder.model.validator.UniqueUsername;
import jakarta.validation.constraints.*;

public class UserRegisterBindingModel {
    @Size(min = 2, max = 20, message = "Username must be between 2 and 20 characters!")
    @UniqueUsername
    @NotBlank(message = "Username cannot be empty")
    private String username;
    @Size(min = 2,max = 20, message = "Full Name length must be between 2 and 20 characters!")
    @NotBlank(message = "Name cannot be empty")
    private String fullName;
    @Email(message = "Entered email is not in valid format")
    @NotBlank(message = "Email cannot be empty")
    private String email;
    @Positive(message = "Age must be positive number")
    @Max(value = 90, message = "Age must be less then 90 years")
    private Integer age;
    @Size(min = 2, max = 20, message = "Password must be between 2 and 20 characters")
    @NotBlank(message = "Password cannot be empty")
    private String password;
    @Size(min = 2, max = 20)
    private String confirmPassword;

    private boolean isValidPassword = false;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public boolean isValidPassword() {
        return password.equals(confirmPassword);
    }

    public void setValidPassword(boolean validPassword) {
        isValidPassword = validPassword;
    }
}
