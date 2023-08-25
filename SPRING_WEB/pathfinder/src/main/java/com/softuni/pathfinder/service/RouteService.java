package com.softuni.pathfinder.service;

import com.softuni.pathfinder.model.bindingModel.PictureAddBindingModel;
import com.softuni.pathfinder.model.entity.RouteEntity;
import com.softuni.pathfinder.model.serviceModel.AddRouteServiceModel;
import com.softuni.pathfinder.model.viewModel.RouteDetailsViewModel;
import com.softuni.pathfinder.model.viewModel.RouteViewModel;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

public interface RouteService {
    List<RouteViewModel> findAllRoutesView();

    RouteDetailsViewModel findRouteViewById(Long id);

    void addRoute(AddRouteServiceModel addRouteServiceModel, Long id);

    RouteEntity findEntityById(Long routeId);

    void addPicture(PictureAddBindingModel pictureAddBindingModel, Long routeId, Principal principal) throws IOException;

    List<RouteViewModel> getRoutesByCategoryName(String categoryName);
}
