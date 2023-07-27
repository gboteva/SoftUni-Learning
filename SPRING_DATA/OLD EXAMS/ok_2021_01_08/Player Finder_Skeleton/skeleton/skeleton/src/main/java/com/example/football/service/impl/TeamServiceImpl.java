package com.example.football.service.impl;

import com.example.football.models.dto.TeamSeedDto;
import com.example.football.models.entity.Team;
import com.example.football.repository.TeamRepository;
import com.example.football.service.TeamService;
import com.example.football.service.TownService;
import com.example.football.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {
    public static final String TEAM_INPUT_PATH = "src/main/resources/files/json/teams.json";
    private final TeamRepository teamRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;
    private final TownService townService;

    public TeamServiceImpl(TeamRepository teamRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson, TownService townService) {
        this.teamRepository = teamRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
        this.townService = townService;
    }


    @Override
    public boolean areImported() {
        return this.teamRepository.count() > 0;
    }

    @Override
    public String readTeamsFileContent() throws IOException {
        return Files.readString(Path.of(TEAM_INPUT_PATH));
    }

    @Override
    public String importTeams() throws IOException {
        TeamSeedDto[] teamSeedDtos = gson.fromJson(readTeamsFileContent(), TeamSeedDto[].class);

        StringBuilder sb = new StringBuilder();

       Arrays.stream(teamSeedDtos)
                .filter(dto -> {
                    boolean isValid = validationUtil.isValid(dto)
                            && !isExistTeam(dto.getName());

                    if (isValid) {
                        sb.append(String.format("Successfully imported Team %s - %d",
                                dto.getName(), dto.getFanBase()));
                    } else {
                        sb.append("Invalid Team");
                    }
                    sb.append(System.lineSeparator());

                    return isValid;
                })
                .map(dto -> {
                    Team team = modelMapper.map(dto, Team.class);
                    team.setTown(townService.getTownByName(dto.getTownName()));

                    return team;
                }).forEach(teamRepository::save);

        return sb.toString().trim();
    }

    @Override
    public Team getByTeamName(String teamName) {
        return teamRepository.findByName(teamName);
    }

    private boolean isExistTeam(String teamName) {
        return teamRepository.existsByName(teamName);
    }
}
