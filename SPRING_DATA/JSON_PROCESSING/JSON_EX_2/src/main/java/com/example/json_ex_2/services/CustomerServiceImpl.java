package com.example.json_ex_2.services;

import com.example.json_ex_2.models.DTO.CustomerSeedDTO;
import com.example.json_ex_2.models.DTO.CustomersWithCountOfCarsAndMoneyDTO;
import com.example.json_ex_2.models.DTO.OrderedCustomerDTO;
import com.example.json_ex_2.models.DTO.SaleCarModelAndCustomerNameDTO;
import com.example.json_ex_2.models.entities.Customer;
import com.example.json_ex_2.models.entities.Part;
import com.example.json_ex_2.repositories.CustomerRepository;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CustomerServiceImpl implements CustomerService{

    public static final String INPUT_PATH = "src/main/resources/input/customers.json";
    private final CustomerRepository customerRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, Gson gson, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }


    @Override
    public void seedData() throws IOException {

        if (customerRepository.count() > 0){
            return;
        }

        String jsonContent = Files.readString(Path.of(INPUT_PATH));

        Arrays.stream(gson.fromJson(jsonContent, CustomerSeedDTO[].class))
                .map(dto ->{
                   Customer customer = modelMapper.map(dto, Customer.class);
                    String birthDate = dto.getBirthDate();

                    LocalDateTime parse = LocalDateTime.parse(birthDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss", Locale.US));

                    customer.setBirthDate(parse);
                    return customer;
                } )
                .forEach(customerRepository::save);
    }

    @Override
    public Customer getRandomCustomer() {
        long countOfRepo = customerRepository.count();
        long randomId = ThreadLocalRandom.current().nextLong(1, countOfRepo + 1);

        return customerRepository.findById(randomId).orElse(null);

    }

    @Override
    public String findAllOrderByBirthDateThanByExperience() {
        List<Customer> customers = customerRepository.findAllOrderByBirthDateAndYoungDriver();

        List<OrderedCustomerDTO> dtos = customers.stream().map(customer -> {
            OrderedCustomerDTO dto = modelMapper.map(customer, OrderedCustomerDTO.class);
            dto.getSales().stream().map(sale -> modelMapper.map(customer.getSales(), SaleCarModelAndCustomerNameDTO[].class));
            return dto;
        }).collect(Collectors.toList());


        return   gson.toJson(dtos);

    }

}
