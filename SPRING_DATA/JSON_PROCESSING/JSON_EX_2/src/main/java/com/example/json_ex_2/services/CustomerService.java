package com.example.json_ex_2.services;

import com.example.json_ex_2.models.entities.Customer;
import org.springframework.data.jpa.repository.Query;

import java.io.IOException;

public interface CustomerService {
    void seedData() throws IOException;
    Customer getRandomCustomer();

    String findAllOrderByBirthDateThanByExperience ();
}
