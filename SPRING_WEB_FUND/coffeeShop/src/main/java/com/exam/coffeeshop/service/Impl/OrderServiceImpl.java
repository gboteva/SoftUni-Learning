package com.exam.coffeeshop.service.Impl;

import com.exam.coffeeshop.model.entity.OrderEntity;
import com.exam.coffeeshop.model.service.OrderAddServiceModel;
import com.exam.coffeeshop.model.view.OrderViewModel;
import com.exam.coffeeshop.repository.OrderRepository;
import com.exam.coffeeshop.service.CategoryService;
import com.exam.coffeeshop.service.OrderService;
import com.exam.coffeeshop.service.UserService;
import com.exam.coffeeshop.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;
    private final CategoryService categoryService;

    public OrderServiceImpl(OrderRepository orderRepository, UserService userService, ModelMapper modelMapper, CurrentUser currentUser, CategoryService categoryService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
        this.categoryService = categoryService;
    }

    @Override
    public void addOrder(OrderAddServiceModel orderAddServiceModel) {
        OrderEntity order = modelMapper.map(orderAddServiceModel, OrderEntity.class);
        order.setEmployee(userService.findUserById(currentUser.getId()));
        order.setCategory(categoryService.findByName(orderAddServiceModel.getCategory()));
        orderRepository.save(order);
    }

    @Override
    public List<OrderViewModel> findAll() {
       return orderRepository.findAll().stream()
                .map(orderEntity ->{
                    OrderViewModel orderView = modelMapper.map(orderEntity, OrderViewModel.class);
                    orderView.setCategory(orderEntity.getCategory());
                    return orderView;
                } )
                .collect(Collectors.toList());

    }

    @Override
    public Integer getTotalTime() {
      return  findAll().stream()
                .map(orderViewModel -> orderViewModel.getCategory().getNeededTime())
                .reduce(Integer::sum).orElse(null);

    }

    @Override
    public void removeOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
