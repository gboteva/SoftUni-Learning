package com.example.json_ex_2.services;

import com.example.json_ex_2.models.entities.Supplier;

import java.io.IOException;

public interface SupplierService {
    void seedData() throws IOException;

    Supplier getRandomSupplier();

    String getAllNotImporterSupplier();

}
