package com.example.springautomapping.models.entities;

import java.util.HashSet;
import java.util.Set;

public class ShoppingCart {
    private Set<Game> gameSet;


    public ShoppingCart() {
        this.gameSet = new HashSet<>();
    }

    public Set<Game> getGameSet() {
        return gameSet;
    }

    public void setGameSet(Set<Game> gameSet) {
        this.gameSet = gameSet;
    }


}
