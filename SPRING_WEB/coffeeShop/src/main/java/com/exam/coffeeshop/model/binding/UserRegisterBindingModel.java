package com.exam.coffeeshop.model.binding;

import com.exam.coffeeshop.model.validaton.UniqueEmail;
import com.exam.coffeeshop.model.validaton.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRegisterBindingModel {
    @Size(min = 5, max =20 )
    @NotBlank
    @UniqueUsername
    private String username;
    private String firstName;
    @Size(min = 5, max =20 )
    @NotBlank
    private String lastName;
    @Email
    @NotBlank
    @UniqueEmail
    private String email;

    @Size(min = 3)
    @NotBlank
    private String password;
    @Size(min = 3)
    @NotBlank
    private String confirmPassword;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
