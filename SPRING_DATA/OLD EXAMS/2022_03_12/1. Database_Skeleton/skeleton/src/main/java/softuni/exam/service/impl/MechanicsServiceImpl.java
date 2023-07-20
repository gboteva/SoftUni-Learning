package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.MechanicSeedDTO;
import softuni.exam.models.entity.Mechanic;
import softuni.exam.repository.MechanicsRepository;
import softuni.exam.service.MechanicsService;
import softuni.exam.util.ValidationUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MechanicsServiceImpl implements MechanicsService {
    public static final String MECHANICS_FILE_PATH = "src/main/resources/files/json/mechanics.json";

    private final MechanicsRepository mechanicsRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    public MechanicsServiceImpl(MechanicsRepository mechanicsRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil) {
        this.mechanicsRepository = mechanicsRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return mechanicsRepository.count() > 0;
    }

    @Override
    public String readMechanicsFromFile() throws IOException {
        File file = new File(MECHANICS_FILE_PATH);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();

        String line = reader.readLine();

        while(line != null) {
            sb.append(line).append("\n");

            line = reader.readLine();
        }

        return sb.toString().trim();
        //return Files.readString(Path.of(MECHANICS_FILE_PATH));
    }

    @Override
    public String importMechanics() throws IOException {
        StringBuilder sb = new StringBuilder();

        MechanicSeedDTO[] mechanicSeedDTOS = gson.fromJson(readMechanicsFromFile(), MechanicSeedDTO[].class);

       Arrays.stream(mechanicSeedDTOS).
                filter(dto -> {
                    boolean isValid = validationUtil.isValid(dto)
                            && !isExistByEmailOrFirstName(dto.getEmail(), dto.getFirstName());

                    if (isValid) {
                        sb.append(String.format("Successfully imported mechanic %s %s",
                                dto.getFirstName(), dto.getLastName()));
                    } else {
                        sb.append("Invalid mechanic");
                    }

                    sb.append(System.lineSeparator());


                    return isValid;
                })
                .map(dto -> modelMapper.map(dto, Mechanic.class))
                .forEach(mechanicsRepository::save);


        return sb.toString();
    }

    @Override
    public boolean existByFirstName(String firstName) {

       return mechanicsRepository.existsByFirstName(firstName);
    }

    @Override
    public Mechanic getByFirstName(String firstName) {
            return mechanicsRepository.findByFirstName(firstName);
    }

    private boolean isExistByEmailOrFirstName(String email, String firstName) {
        return mechanicsRepository.existsByEmailOrFirstName(email, firstName);
    }
}
