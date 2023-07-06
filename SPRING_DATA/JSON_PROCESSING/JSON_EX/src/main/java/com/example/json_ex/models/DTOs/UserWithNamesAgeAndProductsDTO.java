package com.example.json_ex.models.DTOs;

import com.example.json_ex.models.entities.Product;
import com.google.gson.annotations.Expose;

import java.util.Set;

public class UserWithNamesAgeAndProductsDTO {

    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private int age;
    @Expose
    private Set<ProductsWithNameAndPriceDTO> soldProducts;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<ProductsWithNameAndPriceDTO> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(Set<ProductsWithNameAndPriceDTO> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
