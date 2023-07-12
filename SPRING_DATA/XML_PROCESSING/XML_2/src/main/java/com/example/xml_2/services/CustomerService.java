package com.example.xml_2.services;

import com.example.xml_2.models.DTO.orderedCustomer.OrderCustomerDTO;
import com.example.xml_2.models.DTO.orderedCustomer.OrderedCustomerRootDTO;
import com.example.xml_2.models.DTO.salesByCustomer.CustomerSalesRootDTO;
import com.example.xml_2.models.DTO.seeds.CustomerRootSeedDTO;
import com.example.xml_2.models.entities.Customer;

public interface CustomerService {
    long getCount();

    void seedData(CustomerRootSeedDTO customerRootSeedDTO);

    Customer getRandomCustomer();

    OrderedCustomerRootDTO getOrderCustomer();

    CustomerSalesRootDTO getCustomersWithSales();
}
