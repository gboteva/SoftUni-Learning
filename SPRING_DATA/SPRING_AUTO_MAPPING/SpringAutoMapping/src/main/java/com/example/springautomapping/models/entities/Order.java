package com.example.springautomapping.models.entities;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @ManyToOne
    private User buyer;

    @ManyToMany()
    private Set<Game> products;

    public Order() {
    }

    public Order(User buyer) {
        this.buyer = buyer;
        this.products = new HashSet<>();
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public Set<Game> getProducts() {
        return products;
    }

    public void setProducts(Set<Game> products) {
        this.products = products;
    }
}
