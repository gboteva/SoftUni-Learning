package com.example.springautomapping.models.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDTO {

    @Email(message = "Incorrect Email!")
    //@Pattern(regexp = "\\w+@[A-z\\-\\_]+[\\.][a-z]+", message = "Incorrect Email!")
    private String email;

    @Size(min = 6, message = "Password size must be at least 6")
    @Pattern(regexp = "([A-Z]+[a-z]+[0-9]+)+", message = "Password should contain at least 1 uppercase letter, 1 lowercase letter and at least 1 digit")
    private String password;
    private String confirmPassword;
    private String fullName;

    public UserDTO(String email, String password, String confirmPassword, String fullName) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.fullName = fullName;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
