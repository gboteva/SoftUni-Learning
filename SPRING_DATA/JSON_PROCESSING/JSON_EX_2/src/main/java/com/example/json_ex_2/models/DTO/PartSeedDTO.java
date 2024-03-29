package com.example.json_ex_2.models.DTO;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class PartSeedDTO {
    @Expose
    private String name;
    @Expose
    private BigDecimal price;
    @Expose
    private Integer quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
