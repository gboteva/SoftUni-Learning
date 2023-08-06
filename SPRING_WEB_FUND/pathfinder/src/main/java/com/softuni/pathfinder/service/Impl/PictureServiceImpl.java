package com.softuni.pathfinder.service.Impl;

import com.softuni.pathfinder.model.entity.PictureEntity;
import com.softuni.pathfinder.model.viewModel.PictureViewModel;
import com.softuni.pathfinder.repository.PictureRepository;
import com.softuni.pathfinder.service.PictureService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class PictureServiceImpl implements PictureService {
    private final PictureRepository pictureRepository;
    private final ModelMapper modelMapper;

    public PictureServiceImpl(PictureRepository pictureRepository, ModelMapper modelMapper) {
        this.pictureRepository = pictureRepository;
        this.modelMapper = modelMapper;
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
}
