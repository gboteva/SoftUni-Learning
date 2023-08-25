package com.softuni.pathfinder.model.bindingModel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserLoginBindingModel {
    @Size(min = 2, max = 20)
    @NotBlank
    private String username;
    @Size(min = 2, max = 20)
    @NotBlank
    private String password;

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
}
