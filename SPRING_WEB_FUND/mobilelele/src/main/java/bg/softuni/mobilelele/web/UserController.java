package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.model.binding.UserLoginBindingModel;
import bg.softuni.mobilelele.model.service.UserLoginServiceModel;
import bg.softuni.mobilelele.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class UserController {
    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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

        UserLoginServiceModel loginUser = new UserLoginServiceModel();

        loginUser.setUsername(userLoginBindingModel.getUsername());
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
    public String register() {
        return "auth-register";
    }


}