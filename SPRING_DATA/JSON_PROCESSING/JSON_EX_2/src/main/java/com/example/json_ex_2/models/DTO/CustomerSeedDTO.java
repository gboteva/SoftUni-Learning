package com.example.json_ex_2.models.DTO;

import com.google.gson.annotations.Expose;

import java.time.LocalDateTime;

public class CustomerSeedDTO {
    @Expose
    private String name;
    @Expose
    private String birthDate;
    @Expose
    private boolean isYoungDriver;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }
}
