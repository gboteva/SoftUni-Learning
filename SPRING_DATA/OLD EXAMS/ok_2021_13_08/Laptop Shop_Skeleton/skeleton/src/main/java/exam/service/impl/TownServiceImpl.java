package exam.service.impl;

import exam.model.dto.TownSeedRootDto;
import exam.model.entity.Town;
import exam.repository.TownRepository;
import exam.service.TownService;
import exam.util.ValidationUtil;
import exam.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class TownServiceImpl implements TownService {
    public static final String TOWN_INPUT_FILE_PATH = "src/main/resources/files/xml/towns.xml";
    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;

    private final ValidationUtil validationUtil;

    public TownServiceImpl(TownRepository townRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validationUtil) {
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
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
    public String importTowns() throws JAXBException, FileNotFoundException {

        TownSeedRootDto townSeedRootDto = xmlParser.fromFile(TOWN_INPUT_FILE_PATH, TownSeedRootDto.class);

        StringBuilder sb = new StringBuilder();

        townSeedRootDto.getTowns().stream()
                .filter(dto -> {
                    boolean isValid = validationUtil.isValid(dto)
                            && !isExistTownByName(dto.getName());

                    if (isValid) {
                        sb.append(String.format("Successfully imported Town %s", dto.getName()));
                    } else {
                        sb.append("Invalid town");
                    }

                    sb.append(System.lineSeparator());

                    return isValid;
                })
                .map(dto -> modelMapper.map(dto, Town.class))
                .forEach(townRepository::save);

        return sb.toString().trim();
    }

    @Override
    public boolean isExistTownByName(String townName) {
        return townRepository.existsByName(townName);
    }

    @Override
    public Town getTownByName(String townName) {
      return townRepository.findByName(townName);

    }
}
