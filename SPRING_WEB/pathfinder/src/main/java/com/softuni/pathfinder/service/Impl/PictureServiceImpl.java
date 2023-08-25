package com.softuni.pathfinder.service.Impl;

import com.softuni.pathfinder.model.cloudinary.CloudinaryImage;
import com.softuni.pathfinder.model.entity.PictureEntity;
import com.softuni.pathfinder.model.viewModel.PictureViewModel;
import com.softuni.pathfinder.repository.PictureRepository;
import com.softuni.pathfinder.service.CloudinaryService;
import com.softuni.pathfinder.service.PictureService;
import com.softuni.pathfinder.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PictureServiceImpl implements PictureService {
    private final PictureRepository pictureRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CloudinaryService cloudinaryService;

    public PictureServiceImpl(PictureRepository pictureRepository, ModelMapper modelMapper, UserService userService, CloudinaryService cloudinaryService) {
        this.pictureRepository = pictureRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public List<PictureViewModel> findAll() {
        return  pictureRepository.findAll()
               .stream()
               .map(pictureEntity -> modelMapper.map(pictureEntity, PictureViewModel.class))
               .collect(Collectors.toList());

    }

    @Override
    public List<PictureViewModel> findAllByRouteId(Long id) {

       List<PictureEntity> pictureEntities = pictureRepository.findAllByRouteId(id);
       return pictureEntities.stream()
               .map(pictureEntity -> modelMapper.map(pictureEntity, PictureViewModel.class))
               .collect(Collectors.toList());
    }

    @Override
    public PictureEntity createPictureEntity(MultipartFile file, String title, Principal principal) throws IOException {
        final CloudinaryImage uploaded = cloudinaryService.uploadImage(file);
        Long loggedUserId = userService.findUserIdByUsername(principal.getName());

        PictureEntity pictureEntity = new PictureEntity();
        pictureEntity.setAuthor(userService.findUserEntityById(loggedUserId));
        pictureEntity.setTitle(title);
        pictureEntity.setUrl(uploaded.getUrl());

        return pictureEntity;
    }

    @Override
    public void savePicture(PictureEntity pictureEntity) {
        pictureRepository.save(pictureEntity);
    }
}
