package com.example.xml_2.services;

import com.example.xml_2.models.DTO.salesWithDiscount.SaleWithDiscountRootDTO;

public interface SaleService {
    long getCount();

    void seedData();

    SaleWithDiscountRootDTO getSalesWithDiscount();
}
