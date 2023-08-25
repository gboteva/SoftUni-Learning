package com.softuni.pathfinder.service;

import com.softuni.pathfinder.model.bindingModel.PictureAddBindingModel;
import com.softuni.pathfinder.model.entity.PictureEntity;
import com.softuni.pathfinder.model.viewModel.PictureViewModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

public interface PictureService {
    List<PictureViewModel> findAll();

    List<PictureViewModel> findAllByRouteId(Long id);

    PictureEntity createPictureEntity(MultipartFile file, String title, Principal principal) throws IOException;

    void savePicture(PictureEntity pictureEntity);
}
