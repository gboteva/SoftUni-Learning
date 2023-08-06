package com.softuni.pathfinder.service.Impl;

import com.softuni.pathfinder.model.entity.RouteEntity;
import com.softuni.pathfinder.model.viewModel.RouteViewModel;
import com.softuni.pathfinder.repository.RouteRepository;
import com.softuni.pathfinder.service.PictureService;
import com.softuni.pathfinder.service.RouteService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {
    private final RouteRepository routeRepository;
    private final ModelMapper modelMapper;
    private final PictureService pictureService;

    public RouteServiceImpl(RouteRepository routeRepository, ModelMapper modelMapper, PictureService pictureService) {
        this.routeRepository = routeRepository;
        this.modelMapper = modelMapper;
        this.pictureService = pictureService;
    }

    @Override
    public List<RouteViewModel> findAll() {
        List<RouteEntity> routes = routeRepository.findAll();

        return routes.stream().map(routeEntity -> {
                    RouteViewModel route = modelMapper.map(routeEntity, RouteViewModel.class);
                    route.setPictures(pictureService.findAllByRouteId(route.getId()));
                    return route;
                })
                .collect(Collectors.toList());
    }
}
