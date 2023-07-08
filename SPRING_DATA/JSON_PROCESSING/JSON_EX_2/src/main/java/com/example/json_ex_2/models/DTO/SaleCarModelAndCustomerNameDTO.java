package com.example.json_ex_2.models.DTO;

import com.google.gson.annotations.Expose;

public class SaleCarModelAndCustomerNameDTO {
    @Expose
    private String carModel;

    @Expose
    private String customerName;

    @Expose
    private double discount;

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
