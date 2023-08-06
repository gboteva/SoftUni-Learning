package com.softuni.pathfinder.web;

import com.softuni.pathfinder.model.bindingModel.UserLoginBindingModel;
import com.softuni.pathfinder.model.bindingModel.UserRegisterBindingModel;
import com.softuni.pathfinder.model.serviceModel.UserLoginServiceModel;
import com.softuni.pathfinder.model.serviceModel.UserRegisterServiceModel;
import com.softuni.pathfinder.model.viewModel.UserViewModel;
import com.softuni.pathfinder.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel(){
        return new UserRegisterBindingModel();
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("isValidPassword", true);
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors() ||
                !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())){
            redirectAttributes.addFlashAttribute("userRegistrationBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationBindingModel", bindingResult);
            redirectAttributes.addFlashAttribute("isValidPassword", false);

            return "redirect:register"; //this is the name of the method because is in the same controller!
        }

        UserRegisterServiceModel userRegServiceModel = modelMapper.map(userRegisterBindingModel, UserRegisterServiceModel.class);

        userService.registerUser(userRegServiceModel);

        return "redirect:login";
    }



    @GetMapping("/login")
    public String login(Model model){
        if (!model.containsAttribute("userLoginBindingModel")){
            model.addAttribute("userLoginBindingModel",new UserLoginBindingModel()) ;
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid UserLoginBindingModel userLoginBindingModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes){

        UserLoginServiceModel user = modelMapper.map(userLoginBindingModel, UserLoginServiceModel.class);

        boolean isUserExist = userService.isUserExist(user);

        if (bindingResult.hasErrors() || !isUserExist){
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
            return "redirect:login";
        }


        userService.login(user);

        return "redirect:/";

    }

    @GetMapping("/logout")
    public String logout(){
        userService.logout();
        return "redirect:/";
    }

    @GetMapping("/profile/{id}")
    public String profile(@PathVariable Long id, Model model){
        UserViewModel userViewModel = userService.findById(id);
        model.addAttribute("userViewModel", userViewModel);

        return "profile";
    }


}
