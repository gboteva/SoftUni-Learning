package com.example.json_ex.repositories;

import com.example.json_ex.models.DTOs.ProductInRangeDTO;
import com.example.json_ex.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findProductsByPriceBetweenAndBuyerIsNullOrderByPriceAsc(BigDecimal lower, BigDecimal upper);

}
