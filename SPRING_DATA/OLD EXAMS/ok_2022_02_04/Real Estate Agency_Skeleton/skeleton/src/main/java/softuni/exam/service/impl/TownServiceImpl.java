package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.TownSeedDto;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TownService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TownServiceImpl implements TownService {
    public static final String TOWN_INPUT_PATH = "src/main/resources/files/json/towns.json";
    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    public TownServiceImpl(TownRepository townRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil) {
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files.readString(Path.of(TOWN_INPUT_PATH));
    }

    @Override
    public String importTowns() throws IOException {

        TownSeedDto[] townSeedDtos = gson.fromJson(readTownsFileContent(), TownSeedDto[].class);

        StringBuilder sb = new StringBuilder();

        Arrays.stream(townSeedDtos).filter(townDto -> {
                    boolean isValid = validationUtil.isValid(townDto)
                            && !isExistTownName(townDto.getTownName());

                    sb.append(isValid ? "Successfully imported town " + townDto.getTownName() + " - " + townDto.getPopulation()
                            : "Invalid town");
                    sb.append(System.lineSeparator());

                    return isValid;
                })
                .map(townDto -> modelMapper.map(townDto, Town.class))
                .forEach(townRepository::save);


        return sb.toString().trim();
    }

    @Override
    public Town findByName(String town) {
        return townRepository.findByTownName(town);
    }

    private boolean isExistTownName(String townName) {
        return townRepository.existsByTownName(townName);
    }


}
