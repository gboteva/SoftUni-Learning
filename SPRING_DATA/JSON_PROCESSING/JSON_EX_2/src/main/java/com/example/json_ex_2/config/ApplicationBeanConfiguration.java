package com.example.json_ex_2.config;

import com.example.json_ex_2.models.DTO.CustomerSeedDTO;
import com.example.json_ex_2.models.entities.Customer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();

//        modelMapper.createTypeMap(CustomerSeedDTO.class, Customer.class)
//                .addMapping(CustomerSeedDTO::getBirthDate,
//                        Customer::setBirthDate);

        return modelMapper;
    }

    @Bean
    public Gson gson(){
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
                .create();
    }

}
