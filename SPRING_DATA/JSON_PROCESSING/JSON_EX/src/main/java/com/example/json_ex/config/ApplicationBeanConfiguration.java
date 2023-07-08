package com.example.json_ex.config;

import com.example.json_ex.models.DTOs.CategoryWithCountOfProductAndProductsSumAndAvgDTO;
import com.example.json_ex.models.entities.Category;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(Category.class, CategoryWithCountOfProductAndProductsSumAndAvgDTO.class)
                .addMapping(Category::getCount, CategoryWithCountOfProductAndProductsSumAndAvgDTO::setProductsCount)
                .addMapping(Category::getAvg, CategoryWithCountOfProductAndProductsSumAndAvgDTO::setAveragePrice)
                .addMapping(Category::getSum, CategoryWithCountOfProductAndProductsSumAndAvgDTO::setTotalRevenue);


        return modelMapper;
    }

    @Bean
    public Gson gson(){
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }

}
