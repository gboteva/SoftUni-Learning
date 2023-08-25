package com.softuni.pathfinder.service.Impl;

import com.softuni.pathfinder.model.bindingModel.PictureAddBindingModel;
import com.softuni.pathfinder.model.entity.PictureEntity;
import com.softuni.pathfinder.model.entity.RouteEntity;
import com.softuni.pathfinder.model.entity.enums.CategoryEnum;
import com.softuni.pathfinder.model.serviceModel.AddRouteServiceModel;
import com.softuni.pathfinder.model.viewModel.PictureViewModel;
import com.softuni.pathfinder.model.viewModel.RouteDetailsViewModel;
import com.softuni.pathfinder.model.viewModel.RouteViewModel;
import com.softuni.pathfinder.repository.RouteRepository;
import com.softuni.pathfinder.service.CategoryService;
import com.softuni.pathfinder.service.PictureService;
import com.softuni.pathfinder.service.RouteService;
import com.softuni.pathfinder.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {
    private final RouteRepository routeRepository;
    private final ModelMapper modelMapper;
    private final PictureService pictureService;
    private final UserService userService;
    private final CategoryService categoryService;


    public RouteServiceImpl(RouteRepository routeRepository, ModelMapper modelMapper, PictureService pictureService, UserService userService, CategoryService categoryService) {
        this.routeRepository = routeRepository;
        this.modelMapper = modelMapper;
        this.pictureService = pictureService;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public List<RouteViewModel> findAllRoutesView() {
        List<RouteEntity> routes = routeRepository.findAll();

        return routes.stream().map(routeEntity -> {
                    RouteViewModel route = modelMapper.map(routeEntity, RouteViewModel.class);
                    List<PictureViewModel> allPictures = pictureService.findAllByRouteId(route.getId());
                    if (allPictures.isEmpty()) {
                        PictureViewModel pictureViewModel = new PictureViewModel();
                        pictureViewModel.setUrl("/images/amazing-routes.jpg");
                        route.setPictures(List.of(pictureViewModel));
                    } else {
                        route.setPictures(allPictures);
                    }

                    return route;
                })
                .collect(Collectors.toList());
    }

    @Override
    public RouteDetailsViewModel findRouteViewById(Long id) {
        Optional<RouteEntity> route = routeRepository.findById(id);
        if(route.isEmpty()){
            throw new IllegalArgumentException("There is no route with id = " + id);
        }

        RouteDetailsViewModel detailView = modelMapper.map(route.get(), RouteDetailsViewModel.class);
        detailView.setPictures(pictureService.findAllByRouteId(id));

        return detailView;

    }

    @Override
    public void addRoute(AddRouteServiceModel addRouteServiceModel, Long id) {
        RouteEntity route = modelMapper.map(addRouteServiceModel, RouteEntity.class);
        route.setAuthor(userService.findUserEntityById(id));

        route.setCategories(addRouteServiceModel.getCategories().stream()
                .map(categoryService::getEntityByName)
                .collect(Collectors.toSet()));

        routeRepository.save(route);
    }

    @Override
    public RouteEntity findEntityById(Long routeId) {
      return  routeRepository.findById(routeId).orElse(null);
    }

    @Override
    public void addPicture(PictureAddBindingModel pictureAddBindingModel, Long routeId, Principal principal) throws IOException {
        RouteEntity route = findEntityById(routeId);

        PictureEntity pictureEntity = pictureService.createPictureEntity(pictureAddBindingModel.getPicture(),
                                                                    pictureAddBindingModel.getTitle(), principal);
        pictureEntity.setRoute(route);

        pictureService.savePicture(pictureEntity);

        route.getPictures().add(pictureEntity);

        routeRepository.save(route);
    }

    @Override
    public List<RouteViewModel> getRoutesByCategoryName(String categoryName) {

       List<RouteEntity> routes = routeRepository.findAllByCategoryName(categoryService.getEntityByName(CategoryEnum.valueOf(categoryName)));

       return routes.stream().map(r -> modelMapper.map(r, RouteViewModel.class))
               .collect(Collectors.toList());

    }
}
