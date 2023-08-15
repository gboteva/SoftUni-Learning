package com.exam.coffeeshop.web;

import com.exam.coffeeshop.service.OrderService;
import com.exam.coffeeshop.service.UserService;
import com.exam.coffeeshop.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final OrderService orderService;
    private final UserService userService;
    private final CurrentUser currentUser;

    public HomeController(OrderService orderService, UserService userService, CurrentUser currentUser) {
        this.orderService = orderService;
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @GetMapping("/home")
    public String home(Model model){
        if (currentUser.getId()==null){
            return "redirect:/users/login";
        }
        model.addAttribute("orders", orderService.findAll());
        model.addAttribute("totalTime", orderService.getTotalTime());
        model.addAttribute("users", userService.getAllUsersWithOrdersCount());
        return "home";
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }


}
