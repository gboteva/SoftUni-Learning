package com.example.football.service.impl;

import com.example.football.models.dto.StatSeedRootDTO;
import com.example.football.models.entity.Stat;
import com.example.football.repository.StatRepository;
import com.example.football.service.StatService;
import com.example.football.util.ValidationUtil;
import com.example.football.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatServiceImpl implements StatService {
    public static final String STAT_INPUT_PATH = "src/main/resources/files/xml/stats.xml";
    private final StatRepository statRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;

    public StatServiceImpl(StatRepository statRepository, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser) {
        this.statRepository = statRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
    }


    @Override
    public boolean areImported() {
        return this.statRepository.count() > 0;
    }

    @Override
    public String readStatsFileContent() throws IOException {
        return Files.readString(Path.of(STAT_INPUT_PATH));
    }

    @Override
    public String importStats() throws JAXBException, FileNotFoundException {

        StatSeedRootDTO statSeedRootDTO = xmlParser.fromFile(STAT_INPUT_PATH, StatSeedRootDTO.class);

        StringBuilder sb = new StringBuilder();

        statSeedRootDTO.getStats()
                .stream().filter(dto -> {
                    boolean isValid = validationUtil.isValid(dto)
                            && !isExistStat(dto.getEndurance(), dto.getPassing(), dto.getShooting());

                    sb.append(isValid ? String.format("Successfully imported Stat %.2f - %.2f - %.2f",
                                    dto.getShooting(), dto.getPassing(), dto.getEndurance())
                                    : "Invalid Stat")
                            .append(System.lineSeparator());

                    return isValid;
                }).map(dto -> modelMapper.map(dto, Stat.class))
                .forEach(statRepository::save);

        return sb.toString().trim();
    }

    @Override
    public Stat getById(Long id) {
        return statRepository.findById(id).orElse(null);
    }

    private boolean isExistStat(Float endurance, Float passing, Float shooting) {
        return statRepository.existsByEnduranceAndPassingAndShooting(endurance, passing, shooting);
    }
}
