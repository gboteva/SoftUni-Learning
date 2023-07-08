package com.example.json_ex.services;

import com.example.json_ex.models.DTOs.ProductInRangeDTO;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    void seedData() throws IOException;

    List<ProductInRangeDTO> saveInfoAboutProductsWithPriceInRangeWithNoBuyer();
}
