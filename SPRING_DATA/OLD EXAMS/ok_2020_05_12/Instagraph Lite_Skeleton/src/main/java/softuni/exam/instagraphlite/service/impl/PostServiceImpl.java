package softuni.exam.instagraphlite.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dto.PostRootSeedDto;
import softuni.exam.instagraphlite.models.entity.Post;
import softuni.exam.instagraphlite.repository.PostRepository;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.service.PostService;
import softuni.exam.instagraphlite.service.UserService;
import softuni.exam.instagraphlite.util.ValidationUtil;
import softuni.exam.instagraphlite.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    public static final String POST_INPUT_PATH = "src/main/resources/files/posts.xml";
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;

    private final PictureService pictureService;
    private final UserService userService;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validationUtil, PictureService pictureService, UserService userService) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.pictureService = pictureService;
        this.userService = userService;
    }

    @Override
    public boolean areImported() {
        return postRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files.readString(Path.of(POST_INPUT_PATH));
    }

    @Override
    public String importPosts() throws IOException, JAXBException {
        PostRootSeedDto postRootSeedDto = xmlParser.fromFile(POST_INPUT_PATH, PostRootSeedDto.class);

        StringBuilder sb = new StringBuilder();

        postRootSeedDto.getPosts().stream()
                .filter(dto -> {
                    boolean isValid = validationUtil.isValid(dto)
                            && pictureService.isExistByPath(dto.getPicture().getPath())
                            && userService.isExistByUsername(dto.getUser().getUsername());

                    if (isValid) {
                        sb.append(String.format("Successfully imported Post, made by %s",
                                dto.getUser().getUsername()));
                    } else {
                        sb.append("Invalid Post");
                    }

                    sb.append(System.lineSeparator());

                    return isValid;
                })
                .map(dto -> {
                    Post post = modelMapper.map(dto, Post.class);
                    post.setPicture(pictureService.getByPath(dto.getPicture().getPath()));
                    post.setUser(userService.getByUsername(dto.getUser().getUsername()));

                    return post;
                }).forEach(postRepository::save);


        return sb.toString().trim();
    }
}
