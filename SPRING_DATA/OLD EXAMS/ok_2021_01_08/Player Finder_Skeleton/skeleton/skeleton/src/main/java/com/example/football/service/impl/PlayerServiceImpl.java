package com.example.football.service.impl;

import com.example.football.models.dto.PlayerSeedRootDto;
import com.example.football.models.entity.Player;
import com.example.football.repository.PlayerRepository;
import com.example.football.service.PlayerService;
import com.example.football.service.StatService;
import com.example.football.service.TeamService;
import com.example.football.service.TownService;
import com.example.football.util.ValidationUtil;
import com.example.football.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {

    public static final String PLAYER_INPUT_PATH = "src/main/resources/files/xml/players.xml";

    private final PlayerRepository playerRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;

    private final TownService townService;

    private final StatService statService;

    private final TeamService teamService;

    public PlayerServiceImpl(PlayerRepository playerRepository, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser, TownService townService, StatService statService, TeamService teamService) {
        this.playerRepository = playerRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
        this.townService = townService;
        this.statService = statService;
        this.teamService = teamService;
    }

    @Override
    public boolean areImported() {
        return this.playerRepository.count() > 0;
    }

    @Override
    public String readPlayersFileContent() throws IOException {
        return
                Files.readString(Path.of(PLAYER_INPUT_PATH));
    }

    @Override
    public String importPlayers() throws JAXBException, FileNotFoundException {
        PlayerSeedRootDto playerSeedRootDto = xmlParser.fromFile(PLAYER_INPUT_PATH, PlayerSeedRootDto.class);

        StringBuilder sb = new StringBuilder();

         playerSeedRootDto.getPlayers()
                .stream().filter(dto -> {
                    boolean isValid = validationUtil.isValid(dto)
                            && !isExistByEmail(dto.getEmail());

                    sb.append(isValid ? String.format("Successfully imported Player %s %s - %s",
                                    dto.getFirstName(), dto.getLastName(), dto.getPosition())
                                    : "Invalid Player")
                            .append(System.lineSeparator());

                    return isValid;
                })
                .map(dto -> {
                    Player player = modelMapper.map(dto, Player.class);
                    player.setTown(townService.getTownByName(dto.getTown().getName()));
                    player.setTeam(teamService.getByTeamName(dto.getTeam().getName()));
                    player.setStat(statService.getById(dto.getStat().getId()));

                    return player;
                }).forEach(playerRepository::save);

        return sb.toString().trim();
    }

    private boolean isExistByEmail(String email) {
        return playerRepository.existsByEmail(email);
    }

    @Override
    public String exportBestPlayers() {
        LocalDate lower = LocalDate.parse("1995-01-01");
        LocalDate upper =  LocalDate.parse("2003-01-01");
        List<Player> players = playerRepository.getBestPlayers(lower,upper);

        StringBuilder sb = new StringBuilder();

        players.forEach(sb::append);

        return sb.toString().trim();
    }
}
