package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CountrySeedDTO;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CountryService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {
    public static final String COUNTRY_INPUT_FILE = "src/main/resources/files/json/countries.json";
    private final CountryRepository countryRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;

    private final ValidationUtil validationUtil;

    public CountryServiceImpl(CountryRepository countryRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil) {
        this.countryRepository = countryRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }


    @Override
    public boolean areImported() {
        return this.countryRepository.count() > 0;
    }

    @Override
    public String readCountriesFromFile() throws IOException {
        return Files.readString(Path.of(COUNTRY_INPUT_FILE));
    }

    @Override
    public String importCountries() throws IOException {
        CountrySeedDTO[] countrySeedDTOs = gson.fromJson(readCountriesFromFile(), CountrySeedDTO[].class);

        StringBuilder sb = new StringBuilder();

            Arrays.stream(countrySeedDTOs)
                    .filter(dto -> {
                        boolean isValid = validationUtil.isValid(dto)
                                && !isEntityExist(dto.getCountryName());
                        if (isValid) {
                            sb.append(String.format("Successfully imported country %s - %s",
                                    dto.getCountryName(), dto.getCurrency()));
                        } else {
                            sb.append("Invalid country");
                        }

                        sb.append(System.lineSeparator());

                        return isValid;
                    })
                    .map(countrySeedDTO -> modelMapper.map(countrySeedDTO, Country.class))
                    .forEach(countryRepository::save);


        return sb.toString().trim();
    }

    @Override
    public Country findById(Long country) {
        return countryRepository.findById(country).orElse(null);
    }

    private boolean isEntityExist(String countryName) {
        return countryRepository.existsByCountryName(countryName);
    }
}
