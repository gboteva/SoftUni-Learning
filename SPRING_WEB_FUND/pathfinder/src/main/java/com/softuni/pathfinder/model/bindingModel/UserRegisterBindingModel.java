package com.softuni.pathfinder.model.bindingModel;

import com.softuni.pathfinder.model.validator.UniqueUsername;
import jakarta.validation.constraints.*;

public class UserRegisterBindingModel {
    @Size(min = 2, max = 20)
    @UniqueUsername
    @NotBlank
    private String username;
    @Size(min = 2,max = 20)
    @NotBlank
    private String fullName;
    @Email
    @NotBlank
    private String email;
    @Positive
    @Max(90)
    private Integer age;
    @Size(min = 2, max = 20)
    @NotBlank
    private String password;
    @Size(min = 2, max = 20)
    private String confirmPassword;

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
}
