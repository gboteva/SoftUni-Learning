package com.example.xml.services;

import com.example.xml.models.DTO.ProductInRange.ProductInRangeDTO;
import com.example.xml.models.DTO.ProductInRange.ProductRootDTO;
import com.example.xml.models.DTO.SeedDTO.ProductSeedRootDTO;
import com.example.xml.models.entities.Product;

import java.util.List;

public interface ProductService {

    Long getCount();

    void seedData(ProductSeedRootDTO productSeedRootDTO);

    ProductRootDTO getProductWithNoBuyer();
}
