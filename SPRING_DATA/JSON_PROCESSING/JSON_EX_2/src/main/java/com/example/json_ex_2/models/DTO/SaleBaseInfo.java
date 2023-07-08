package com.example.json_ex_2.models.DTO;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class SaleBaseInfo {

    @Expose
    private CarBaseInfoDTO carBaseInfoDTO;

    @Expose
    private String CustomerName;

    @Expose
    private double discount;

    @Expose
    BigDecimal price;

    @Expose
    BigDecimal priceWithDiscount;

    public CarBaseInfoDTO getCarBaseInfo() {
        return carBaseInfoDTO;
    }

    public void setCarBaseInfo(CarBaseInfoDTO carBaseInfoDTO) {
        this.carBaseInfoDTO = carBaseInfoDTO;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(BigDecimal priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }
}
