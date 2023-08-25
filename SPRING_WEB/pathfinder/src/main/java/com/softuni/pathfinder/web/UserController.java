package com.softuni.pathfinder.web;

import com.softuni.pathfinder.model.bindingModel.UserLoginBindingModel;
import com.softuni.pathfinder.model.bindingModel.UserRegisterBindingModel;
import com.softuni.pathfinder.model.serviceModel.UserRegisterServiceModel;
import com.softuni.pathfinder.model.viewModel.UserViewModel;
import com.softuni.pathfinder.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @ModelAttribute()
    public UserRegisterBindingModel userRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }

    @GetMapping("/register")
    public String register(UserRegisterBindingModel userRegisterBindingModel, Model model, HttpSession httpSession) {
        model.addAttribute("userRegisterBindingModel", userRegisterBindingModel);
        model.addAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel",
                httpSession.getAttribute("errors"));
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult,
                                  HttpSession httpSession) {

        if (bindingResult.hasErrors() ||
                !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            httpSession.setAttribute("userRegisterBindingModel", userRegisterBindingModel);
            httpSession.setAttribute("errors", bindingResult);

            return "redirect:register"; //this is the name of the method because is in the same controller!
        }

        UserRegisterServiceModel userRegServiceModel = modelMapper.map(userRegisterBindingModel, UserRegisterServiceModel.class);

        userService.registerUser(userRegServiceModel);

        return "redirect:login";
    }

    @ModelAttribute
    public UserLoginBindingModel userLoginBindingModel(){
        return new UserLoginBindingModel();
    }

    @GetMapping("/login")
    public String login(Model model, HttpSession httpSession, UserLoginBindingModel userLoginBindingModel) {

        model.addAttribute("userLoginBindingModel", userLoginBindingModel);
        model.addAttribute("org.springframework.validation.BindingResult.userLoginBindingModel",
                httpSession.getAttribute("errors"));
        return "login";
    }


    @GetMapping("/profile/{name}")
    public String profile(@PathVariable("name") String username, Model model, Principal principal) {

        Long userId = userService.findUserIdByUsername(principal.getName());

        UserViewModel userViewModel = userService.findUserViewModelById(userId);
        model.addAttribute("userViewModel", userViewModel);
        model.addAttribute("username", userId);

        return "profile";
    }


}
