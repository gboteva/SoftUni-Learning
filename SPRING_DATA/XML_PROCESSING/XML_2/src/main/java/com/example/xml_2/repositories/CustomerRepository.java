package com.example.xml_2.repositories;

import com.example.xml_2.models.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("select c from Customer c order by c.birthDate, c.isYoungDriver")
    List<Customer> findAllOrderByBirthdateAndIsYoungDriver();

    @Query("select c from Customer c where size(c.sales) > 0")
    List<Customer> findAllCustomersWithSales();
}
