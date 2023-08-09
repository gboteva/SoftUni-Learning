package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.model.binding.RegisterBindingModel;
import bg.softuni.mobilelele.model.binding.UserLoginBindingModel;
import bg.softuni.mobilelele.model.service.UserLoginServiceModel;
import bg.softuni.mobilelele.model.service.UserRegisterServiceModel;
import bg.softuni.mobilelele.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;


@Controller
@RequestMapping("/users")
public class UserController {
    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public String login(Model model, HttpSession httpSession, UserLoginBindingModel loginModel) {

        model.addAttribute("loginModel", loginModel);

        model.addAttribute("org.springframework.validation.BindingResult.loginModel",
                httpSession.getAttribute("errors"));

        return "auth-login";
    }

    @ModelAttribute
    public UserLoginBindingModel loginModel(){
        return new UserLoginBindingModel();
    }


    @PostMapping("/login")
    public String loginConfirm(@Valid UserLoginBindingModel userLoginBindingModel,
                        BindingResult bindingResult,
                        HttpSession httpSession,
                               Model model) {

        if (bindingResult.hasErrors()){
            model.addAttribute("loginModel", userLoginBindingModel);
            httpSession.setAttribute("userLoginBindingModel",userLoginBindingModel);
            httpSession.setAttribute("errors", bindingResult);
            return "redirect:login";
        }

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


    @GetMapping("/logout")
    public String logout(){
        userService.logout();
        return "redirect:/";
    }

    @ModelAttribute
    public RegisterBindingModel registerBindingModel(){
        return new RegisterBindingModel();
    }

    @GetMapping("/register")
    public String register(RegisterBindingModel registerBindingModel, HttpSession httpSession, Model model) {
        model.addAttribute("registerBindingModel", registerBindingModel);
        model.addAttribute("org.springframework.validation.BindingResult.registerBindingModel",
                httpSession.getAttribute("registerBindingModelErrors"));

        return "auth-register";
    }

    @PostMapping("/register")
    public String register(@Valid RegisterBindingModel registerBindingModel,
                           BindingResult bindingResult,
                           HttpSession session){

        if (bindingResult.hasErrors()){
            session.setAttribute("registerBindingModel", registerBindingModel);
            session.setAttribute("registerBindingModelErrors", bindingResult);
            return "redirect:register";
        }

        UserRegisterServiceModel userServiceModel = modelMapper.map(registerBindingModel, UserRegisterServiceModel.class);

        userService.registerAndLoginUser(userServiceModel);

        return "redirect:/";
    }


}
