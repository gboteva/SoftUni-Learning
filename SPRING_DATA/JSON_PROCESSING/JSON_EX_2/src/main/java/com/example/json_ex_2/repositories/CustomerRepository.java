package com.example.json_ex_2.repositories;

import com.example.json_ex_2.models.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {


    @Query("SELECT c FROM Customer c ORDER BY c.birthDate, c.isYoungDriver")
    List<Customer> findAllOrderByBirthDateAndYoungDriver();

}
