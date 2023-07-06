package com.example.json_ex_2.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "sales")
public class Sale extends BaseEntity{
    @OneToOne
    private Car car;

    @ManyToOne()
    private Customer customer;

    @Column()
    private double discount;

    public Sale() {
    }

    public Sale(Car car, Customer customer, double discount) {
        this.car = car;
        this.customer = customer;
        this.discount = discount;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
