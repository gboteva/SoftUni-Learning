package com.exam.coffeeshop.model.view;

public class UsersAndCountOrderViewModel {
    private String username;
    private int ordersCount;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getOrdersCount() {
        return ordersCount;
    }

    public void setOrdersCount(int ordersCount) {
        this.ordersCount = ordersCount;
    }
}
