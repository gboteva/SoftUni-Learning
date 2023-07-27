package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.PlaneRootSeedDto;
import softuni.exam.models.entities.Plane;
import softuni.exam.repository.PlaneRepository;
import softuni.exam.service.PlaneService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaneServiceImpl implements PlaneService {
    public static final String PLANE_INPUT_PATH = "src/main/resources/files/xml/planes.xml";
    private final PlaneRepository planeRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;

    public PlaneServiceImpl(PlaneRepository planeRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validationUtil) {
        this.planeRepository = planeRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return planeRepository.count() > 0;
    }

    @Override
    public String readPlanesFileContent() throws IOException {
        return Files.readString(Path.of(PLANE_INPUT_PATH));
    }

    @Override
    public String importPlanes() throws JAXBException, FileNotFoundException {
        PlaneRootSeedDto planeRootSeedDto = xmlParser.fromFile(PLANE_INPUT_PATH, PlaneRootSeedDto.class);
        StringBuilder sb = new StringBuilder();

        planeRootSeedDto.getPlanes().stream()
                .filter(dto -> {
                    boolean isValid = validationUtil.isValid(dto)
                            && !existByRegisterNumber(dto.getRegisterNumber());

                    if (isValid) {
                        sb.append(String.format("Successfully imported Plane %s", dto.getRegisterNumber()));
                    } else {
                        sb.append("Invalid Plane");
                    }

                    sb.append(System.lineSeparator());

                    return isValid;
                }).map(dto ->  modelMapper.map(dto, Plane.class))
                .forEach(planeRepository::save);

        return sb.toString().trim();
    }

    public boolean existByRegisterNumber(String registerNumber) {
        return planeRepository.existsByRegisterNumber(registerNumber);
    }

    @Override
    public Plane getByNumber(String registerNumber) {
       return planeRepository.findByRegisterNumber(registerNumber);
    }
}
