package com.example.xml_2.config;

import com.example.xml_2.models.DTO.seeds.CustomerRootSeedDTO;
import com.example.xml_2.models.DTO.seeds.CustomerSeedDTO;
import com.example.xml_2.models.entities.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper modelMapper(){
//        ModelMapper modelMapper = new ModelMapper();
//
//        modelMapper.createTypeMap(CustomerSeedDTO.class, Customer.class)
//                .addMapping(CustomerSeedDTO::getBirthdate, (customer, date) -> {
//                    customer.setBirthDate(LocalDateTime.parse(date.toString(),
//                            DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:mm:ss")));
//                });
        return new ModelMapper();
    }

}
