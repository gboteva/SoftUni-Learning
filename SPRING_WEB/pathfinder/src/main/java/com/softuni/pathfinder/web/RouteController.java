package com.softuni.pathfinder.web;

import com.softuni.pathfinder.model.bindingModel.PictureAddBindingModel;
import com.softuni.pathfinder.model.bindingModel.RouteAddBindingModel;
import com.softuni.pathfinder.model.entity.enums.CategoryEnum;
import com.softuni.pathfinder.model.entity.enums.LevelEnum;
import com.softuni.pathfinder.model.serviceModel.AddRouteServiceModel;
import com.softuni.pathfinder.model.viewModel.RouteDetailsViewModel;
import com.softuni.pathfinder.model.viewModel.RouteViewModel;
import com.softuni.pathfinder.service.RouteService;
import com.softuni.pathfinder.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/routes")
public class RouteController {
    private final RouteService routeService;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public RouteController(RouteService routeService, ModelMapper modelMapper, UserService userService) {
        this.routeService = routeService;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }


    @GetMapping("/all")
    public String routes(Model model){
        model.addAttribute("routes", routeService.findAllRoutesView());
        return "routes";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model){

        RouteDetailsViewModel routeViewById = routeService.findRouteViewById(id);

        model.addAttribute("route", routeService.findRouteViewById(id));

        return "route-details";
    }

    @ModelAttribute
    public RouteAddBindingModel routeAddBindingModel(){
        return new RouteAddBindingModel();
    }

    @GetMapping("/add-route")
    public String addRoute(Model model, RouteAddBindingModel routeAddBindingModel, HttpSession httpSession){

        model.addAttribute("routeAddBindingModel",routeAddBindingModel);
        model.addAttribute("org.springframework.validation.BindingResult.routeAddBindingModel",
                httpSession.getAttribute("errors"));
        model.addAttribute("categories", CategoryEnum.values());
        model.addAttribute("level", LevelEnum.values());
        return "add-route";
    }

    @PostMapping("/add-route")
    public String addRouteConfirm(RouteAddBindingModel routeAddBindingModel,
                                  BindingResult bindingResult,
                                  HttpSession httpSession,
                                  Principal principal){

        if (bindingResult.hasErrors()){
            httpSession.setAttribute("routeAddBindingModel", routeAddBindingModel);
            httpSession.setAttribute("error", bindingResult);
            return "redirect:addRoute";
        }

        Long loggedUserId = userService.findUserIdByUsername(principal.getName());

        AddRouteServiceModel addRouteServiceModel = modelMapper.map(routeAddBindingModel, AddRouteServiceModel.class);
        routeService.addRoute(addRouteServiceModel, loggedUserId );

        return "redirect:all";
    }


    @PostMapping("/details/{id}/pictures/add")
    public String addPictureToRoute(@PathVariable("id") Long routeId,
                                    PictureAddBindingModel pictureAddBindingModel,
                                    Model model, Principal principal) throws IOException {

        model.addAttribute(pictureAddBindingModel);

        routeService.addPicture(pictureAddBindingModel, routeId, principal);

        return "redirect:/routes/all";
    }

    @ModelAttribute
    public  PictureAddBindingModel pictureAddBindingModel(){
        return new PictureAddBindingModel();
    }


    @GetMapping("/pedestrian")
    public String getPedestrianRoutes(Model model){
        List<RouteViewModel> pedestrian = routeService.getRoutesByCategoryName("PEDESTRIAN");

        model.addAttribute("routes", pedestrian);

        return "pedestrian";
    }

    @GetMapping("/bicycle")
    public String getBicycleRoutes(Model model){
        List<RouteViewModel> bicycle = routeService.getRoutesByCategoryName("BICYCLE");
        model.addAttribute("routes", bicycle);
        return "bicycle";
    }

    @GetMapping("/motorcycle")
    public String getMotorcycleRoutes(Model model){
        List<RouteViewModel> motorcycle = routeService.getRoutesByCategoryName("MOTORCYCLE");
        model.addAttribute("routes", motorcycle);
        return "motorcycle";
    }

    @GetMapping("/car")
    public String getCarRoutes(Model model){
        List<RouteViewModel> car = routeService.getRoutesByCategoryName("CAR");
        model.addAttribute("routes", car);
        return "car";
    }
}
