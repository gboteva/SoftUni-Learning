package com.example.springautomapping.models.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "users")
public class User extends BaseEntity{

    @Column
    private String email;
    @Column
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @Column
    private boolean isAdmin;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Game> games;


    @Transient
    private ShoppingCart shoppingCart;

    public User(String email, String password, String fullName, boolean isAdmin) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.isAdmin = isAdmin;
        this.games = new HashSet<>();
        this.shoppingCart = new ShoppingCart();
    }

    protected User() {    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

}
