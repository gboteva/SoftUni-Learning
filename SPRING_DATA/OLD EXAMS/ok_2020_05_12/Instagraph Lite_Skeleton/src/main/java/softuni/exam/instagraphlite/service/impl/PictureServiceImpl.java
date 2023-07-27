package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dto.PictureSeedDto;
import softuni.exam.instagraphlite.models.entity.Picture;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class PictureServiceImpl implements PictureService {
    public static final String PICTURE_INPUT_PATH = "src/main/resources/files/pictures.json";
    private final PictureRepository pictureRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    public PictureServiceImpl(PictureRepository pictureRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson) {
        this.pictureRepository = pictureRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.pictureRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files.readString(Path.of(PICTURE_INPUT_PATH));
    }

    @Override
    public String importPictures() throws IOException {
        PictureSeedDto[] pictureSeedDtos = gson.fromJson(readFromFileContent(), PictureSeedDto[].class);

        StringBuilder sb = new StringBuilder();

        Arrays.stream(pictureSeedDtos)
                .filter(dto -> {
                    boolean isValid = validationUtil.isValid(dto)
                            && !isExistByPath(dto.getPath());

                    if (isValid) {
                        sb.append(String.format("Successfully imported Picture, with size %.2f",
                                dto.getSize()));
                    } else {
                        sb.append("Invalid Picture");
                    }

                    sb.append(System.lineSeparator());


                    return isValid;
                })
                .map(dto -> modelMapper.map(dto, Picture.class))
                .forEach(pictureRepository::save);

        return sb.toString().trim();
    }

    @Override
    public boolean isExistByPath(String path) {
      return  pictureRepository.existsByPath(path);
    }

    @Override
    public Picture getByPath(String picturePath) {
      return pictureRepository.findByPath(picturePath);
    }

    @Override
    public String exportPictures() {
        StringBuilder sb = new StringBuilder();

        pictureRepository.findAllBiggerThan30000OrderBySize()
                .forEach(sb::append);

        return sb.toString();
    }
}
