package com.exam.coffeeshop.web;

import com.exam.coffeeshop.model.binding.OrderAddBindingModel;
import com.exam.coffeeshop.model.entity.enums.CategoryNameEnum;
import com.exam.coffeeshop.model.service.OrderAddServiceModel;
import com.exam.coffeeshop.service.OrderService;
import com.exam.coffeeshop.util.CurrentUser;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public OrderController(OrderService orderService, ModelMapper modelMapper, CurrentUser currentUser) {
        this.orderService = orderService;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @GetMapping("/add")
    public String addOrder(Model model){
        if (currentUser.getId()==null){
            return "redirect:/users/login";
        }
        model.addAttribute("categories", CategoryNameEnum.values());
        return "order-add";
    }

    @PostMapping("/add")
    public String addOrderConfirm(@Valid OrderAddBindingModel orderAddBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("orderAddBindingModel",orderAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.orderAddBindingModel", bindingResult);
            return "redirect:add";
        }

        OrderAddServiceModel orderAddServiceModel = modelMapper.map(orderAddBindingModel, OrderAddServiceModel.class);

        orderService.addOrder(orderAddServiceModel);

        return "redirect:/home";
    }

    @GetMapping("/ready/{id}")
    public String readyOrder(@PathVariable Long id){
        if (currentUser.getId() == null){
            return "redirect:/users/login";
        }
        orderService.removeOrder(id);
        return "redirect:/home";
    }

    @ModelAttribute
    public OrderAddBindingModel orderAddBindingModel(){
        return new OrderAddBindingModel();
    }
}
