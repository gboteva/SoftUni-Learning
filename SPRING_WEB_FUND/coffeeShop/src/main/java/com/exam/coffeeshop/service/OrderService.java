package com.exam.coffeeshop.service;

import com.exam.coffeeshop.model.service.OrderAddServiceModel;
import com.exam.coffeeshop.model.view.OrderViewModel;

import java.util.List;

public interface OrderService {
    void addOrder(OrderAddServiceModel orderAddServiceModel);

    List<OrderViewModel> findAll();

    Integer getTotalTime();

    void removeOrder(Long id);
}
