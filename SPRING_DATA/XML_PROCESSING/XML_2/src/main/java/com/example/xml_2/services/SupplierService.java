package com.example.xml_2.services;


import com.example.xml_2.models.DTO.localSuppliers.LocalSupplierRootDTO;
import com.example.xml_2.models.DTO.seeds.SupplierRootSeedDTO;
import com.example.xml_2.models.entities.Supplier;

public interface SupplierService {

    long getCount();
    void seedData(SupplierRootSeedDTO supplierRootSeedDTO);

    Supplier getRandomSupplier();

    LocalSupplierRootDTO getLocalSuppliers();
}
