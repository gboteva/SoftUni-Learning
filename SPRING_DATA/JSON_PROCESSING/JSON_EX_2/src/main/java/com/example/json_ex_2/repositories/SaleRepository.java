package com.example.json_ex_2.repositories;

import com.example.json_ex_2.models.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(value = "SELECT cust.name, SUM(p.price) As 'price', COUNT(c.id) AS 'count', s.discount " +
            "FROM sales AS s " +
            "JOIN customers AS cust ON cust.id = s.customer_id " +
            "JOIN cars as c ON s.car_id = c.id " +
            "JOIN cars_parts AS cp ON c.id = cp.car_id " +
            "JOIN parts AS p ON cp.parts_id = p.id " +
            "GROUP BY s.customer_id " +
            "ORDER BY SUM(p.price) DESC, COUNT(c.id) DESC;", nativeQuery = true)
    List<String> findAllInfoAboutCustomersWithSales();

}
