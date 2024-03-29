package com.example.football.service.impl;

import com.example.football.models.dto.TownSeedDTO;
import com.example.football.models.entity.Town;
import com.example.football.repository.TownRepository;
import com.example.football.service.TownService;
import com.example.football.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;


@Service
public class TownServiceImpl implements TownService {
    public static final String TOWN_INPUT_FILE_PATH = "src/main/resources/files/json/towns.json";
    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    private final Gson gson;

    public TownServiceImpl(TownRepository townRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson) {
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files.readString(Path.of(TOWN_INPUT_FILE_PATH));
    }

    @Override
    public String importTowns() throws IOException {
        TownSeedDTO[] townSeedDTOS = gson.fromJson(readTownsFileContent(), TownSeedDTO[].class);

        StringBuilder sb = new StringBuilder();

        Arrays.stream(townSeedDTOS)
                .filter(dto -> {
                    boolean isValid = validationUtil.isValid(dto)
                            && !isExistByName(dto.getName());

                    sb.append(isValid ? String.format("Successfully imported Town %s - %d",
                                    dto.getName(), dto.getPopulation())
                                    : "Invalid Town")
                            .append(System.lineSeparator());

                    return isValid;
                })
                .map(dto -> modelMapper.map(dto, Town.class))
                .forEach(townRepository::save);


        return sb.toString().trim();
    }


    private boolean isExistByName(String townName) {
        return townRepository.existsByName(townName);
    }

    @Override
    public Town getTownByName(String townName) {
        return townRepository.findByName(townName) ;
    }
}
