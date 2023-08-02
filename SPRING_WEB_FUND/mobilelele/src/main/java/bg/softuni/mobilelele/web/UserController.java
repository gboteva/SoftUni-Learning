package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.model.binding.RegisterBindingModel;
import bg.softuni.mobilelele.model.binding.UserLoginBindingModel;
import bg.softuni.mobilelele.model.service.UserLoginServiceModel;
import bg.softuni.mobilelele.model.service.UserRegisterServiceModel;
import bg.softuni.mobilelele.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;


@Controller
public class UserController {
    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("users/login")
    public String login() {
        return "auth-login";
    }

    @PostMapping("users/login")
    public String login(UserLoginBindingModel userLoginBindingModel) {

        UserLoginServiceModel loginUser = modelMapper.map(userLoginBindingModel, UserLoginServiceModel.class);
        loginUser.setRawPassword(userLoginBindingModel.getPassword());

        boolean loginSuccessful = userService.login(loginUser);

        LOGGER.info("User with username {} tried to login - Success {}",
                userLoginBindingModel.getUsername(), loginSuccessful);

        if (loginSuccessful){
            return "redirect:/";
        }

        return "redirect:login";
    }


    @GetMapping("users/logout")
    public String logout(){
        userService.logout();
        return "redirect:/";
    }


    @GetMapping("users/register")
    public String register(Model model) {
        return "auth-register";
    }

    @PostMapping("users/register")
    public String register(@Valid RegisterBindingModel userModel, Model model,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userModel", userModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel", bindingResult);

            return "redirect:/users/register";
        }

        UserRegisterServiceModel userServiceModel = modelMapper.map(userModel, UserRegisterServiceModel.class);

        userService.registerAndLoginUser(userServiceModel);

        return "redirect:/";
    }


}
