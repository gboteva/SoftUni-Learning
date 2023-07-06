package com.example.json_ex_2.models.DTO;

import com.google.gson.annotations.Expose;

import java.time.LocalDateTime;
import java.util.Set;

public class OrderedCustomerDTO {
    @Expose
    private Long id;

    @Expose
    private String name;

    @Expose
    private LocalDateTime birthDate;

    @Expose
    private boolean isYoungDriver;

    @Expose
    Set<SaleCarModelAndCustomerNameDTO> sales;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }

    public Set<SaleCarModelAndCustomerNameDTO> getSales() {
        return sales;
    }

    public void setSales(Set<SaleCarModelAndCustomerNameDTO> sales) {
        this.sales = sales;
    }
}
