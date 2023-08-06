package com.softuni.pathfinder.web;

import com.softuni.pathfinder.model.viewModel.RouteViewModel;
import com.softuni.pathfinder.service.PictureService;
import com.softuni.pathfinder.service.RouteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class RouteController {
    private final RouteService routeService;
    private final PictureService pictureService;

    public RouteController(RouteService routeService, PictureService pictureService) {
        this.routeService = routeService;
        this.pictureService = pictureService;
    }


    @GetMapping("/routes")
    public String routes(Model model){
        List<RouteViewModel> all = routeService.findAll();
        model.addAttribute("routes", routeService.findAll());
        return "routes";
    }

    @GetMapping("/routes/details/{id}")
    public String details(@PathVariable Long id){
        return "route-details";
    }

}
