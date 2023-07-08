package com.example.json_ex_2.models.DTO;

import com.google.gson.annotations.Expose;

import java.util.List;

public class CarWithMakeModelAndDistanceDTO {

    @Expose
    private String make;

    @Expose
    private String model;

    @Expose
    private Long travelledDistance;

    @Expose
    List<PartWithNameAndPriceDTO> parts;

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

    public List<PartWithNameAndPriceDTO> getParts() {
        return parts;
    }

    public void setParts(List<PartWithNameAndPriceDTO> parts) {
        this.parts = parts;
    }
}
