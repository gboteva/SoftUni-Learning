package com.example.json_ex.models.DTOs;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class CategoryWithCountOfProductAndProductsSumAndAvgDTO {
    @Expose
    private String name;
    @Expose
    private long productsCount;

    @Expose
    private BigDecimal averagePrice;

    @Expose
    private BigDecimal totalRevenue;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getProductsCount() {
        return productsCount;
    }

    public void setProductsCount(long productsCount) {
        this.productsCount = productsCount;
    }

    public BigDecimal getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(BigDecimal averagePrice) {
        this.averagePrice = averagePrice;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
