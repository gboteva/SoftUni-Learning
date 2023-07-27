package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dto.UserSeedDto;
import softuni.exam.instagraphlite.models.entity.User;
import softuni.exam.instagraphlite.repository.UserRepository;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.service.UserService;
import softuni.exam.instagraphlite.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    public static final String USER_INPUT_PATH = "src/main/resources/files/users.json";
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    private final PictureService pictureService;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil, PictureService pictureService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.pictureService = pictureService;
    }

    @Override
    public boolean areImported() {
        return userRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files.readString(Path.of(USER_INPUT_PATH));
    }

    @Override
    public String importUsers() throws IOException {
        UserSeedDto[] userSeedDtos = gson.fromJson(readFromFileContent(), UserSeedDto[].class);

        StringBuilder sb = new StringBuilder();

       Arrays.stream(userSeedDtos)
                .filter(dto -> {
                    boolean isValid = validationUtil.isValid(dto)
                            && pictureService.isExistByPath(dto.getProfilePicture());

                    if (isValid) {
                        sb.append(String.format("Successfully imported User: %s", dto.getUsername()));
                    } else {
                        sb.append("Invalid User");
                    }

                    sb.append(System.lineSeparator());

                    return isValid;
                })
                .map(dto -> {
                    User user = modelMapper.map(dto, User.class);
                    user.setProfilePicture(pictureService.getByPath(dto.getProfilePicture()));
                    return user;
                }).forEach(userRepository::save);

        return sb.toString().trim();
    }

    @Override
    public String exportUsersWithTheirPosts() {
        List<User> users = userRepository.findAllUserWithTheirPosts();
        StringBuilder sb = new StringBuilder();
        for (User user : users) {
            sb.append(String.format("User: %s\n" +
                    "Post count: %d\n", user.getUsername(), user.getPosts().size()));

            user.getPosts().stream()
                    .sorted(Comparator.comparingDouble(a -> a.getPicture().getSize()))
                    .forEach(post ->
                            sb.append(String.format("==Post Details:\n" +
                            "----Caption: %s\n" +
                            "----Picture Size: %.2f\n",
                                    post.getCaption(),
                                    post.getPicture().getSize())));
        }

        return sb.toString();
    }

    @Override
    public boolean isExistByUsername(String username) {
       return userRepository.existsByUsername(username);
    }

    @Override
    public User getByUsername(String username) {
       return userRepository.findByUsername(username);
    }
}
