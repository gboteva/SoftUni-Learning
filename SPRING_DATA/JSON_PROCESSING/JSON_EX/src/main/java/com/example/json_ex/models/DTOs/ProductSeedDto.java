package com.example.json_ex.models.DTOs;

import com.google.gson.annotations.Expose;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class ProductSeedDto {

    @Expose
    @Size(min = 3)
    private String name;

    @Expose
    private BigDecimal price;

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

    public ProductSeedDto(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }
}
