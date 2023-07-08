package com.example.json_ex_2.models.DTO;

import com.google.gson.annotations.Expose;

public class SupplierWithCountOfPartsNotImporterDTO {
    @Expose
    private Long id;

    @Expose
    private String name;
    @Expose
    private int countOfParts;

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

    public int getCountOfParts() {
        return countOfParts;
    }

    public void setCountOfParts(int countOfParts) {
        this.countOfParts = countOfParts;
    }
}
