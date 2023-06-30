package com.example.springautomapping.config;

import com.example.springautomapping.models.dto.GameAddDTO;
import com.example.springautomapping.models.dto.GameViewDetailsDTO;
import com.example.springautomapping.models.entities.Game;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.typeMap(GameAddDTO.class, Game.class)
                .addMappings(mapper -> mapper.map(GameAddDTO::getThumbnailURL, Game::setImageThumbnail));

        modelMapper.typeMap(GameAddDTO.class, Game.class)
                .addMappings(mapper -> mapper.map(GameAddDTO::getDescription, Game::setText));

        modelMapper.typeMap(Game.class, GameViewDetailsDTO.class)
                .addMappings(mapper -> mapper.map(Game::getText, GameViewDetailsDTO::setDescription));

        return modelMapper;
    }
}
