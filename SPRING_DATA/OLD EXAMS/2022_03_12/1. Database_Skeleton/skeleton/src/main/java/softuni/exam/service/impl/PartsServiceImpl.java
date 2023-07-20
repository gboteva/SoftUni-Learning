package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.PartSeedDTO;
import softuni.exam.models.entity.Part;
import softuni.exam.repository.PartsRepository;
import softuni.exam.service.PartsService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// TODO: Implement all methods
@Service
public class PartsServiceImpl implements PartsService {
    public static final String PARTS_FILE_PATH = "src/main/resources/files/json/parts.json";
    private final PartsRepository partsRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    public PartsServiceImpl(PartsRepository partsRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil) {
        this.partsRepository = partsRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return partsRepository.count() > 0;
    }

    @Override
    public String readPartsFileContent() throws IOException {

        return Files.readString(Path.of(PARTS_FILE_PATH));
    }

    @Override
    public String importParts() throws IOException {

        StringBuilder sb = new StringBuilder();

        PartSeedDTO[] partSeedDTOS = gson.fromJson(readPartsFileContent(), PartSeedDTO[].class);

        Arrays.stream(partSeedDTOS).
                filter(dto -> {
                    boolean isValid = validationUtil.isValid(dto)
                            && !isExistPartName(dto.getPartName());

                    if (isValid) {
                        sb.append(String.format("Successfully imported part %s - %.2f",
                                dto.getPartName(), dto.getPrice()));
                    } else {
                        sb.append("Invalid part");
                    }

                    sb.append(System.lineSeparator());

                    return isValid;
                })
                .map(dto -> modelMapper.map(dto, Part.class))
                .forEach(partsRepository::save);

        return sb.toString().trim();
    }

    @Override
    public Part getById(Long id) {
        return partsRepository.findById(id).orElse(null);
    }

    private boolean isExistPartName(String partName) {
        return partsRepository.existsByPartName(partName);
    }
}
