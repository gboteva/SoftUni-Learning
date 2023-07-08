package com.example.json_ex_2.models.DTO;

import com.google.gson.annotations.Expose;

public class CarBaseInfoDTO {
    @Expose
    private String make;
    @Expose
    private String model;

    @Expose
    private Long travelledDistance;

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(Long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }
}
