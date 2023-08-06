package com.softuni.pathfinder.service;

import com.softuni.pathfinder.model.viewModel.PictureViewModel;

import java.util.List;

public interface PictureService {
    List<PictureViewModel> findAll();

    List<PictureViewModel> findAllByRouteId(Long id);

}
