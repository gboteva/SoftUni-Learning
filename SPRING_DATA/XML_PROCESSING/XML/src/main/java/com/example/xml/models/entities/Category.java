package com.example.xml.models.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity{

    @Column(length = 15, nullable = false, unique = true)
    private String name;

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @ManyToMany(mappedBy = "categories", fetch = FetchType.EAGER)
    private Set<Product> products;

    public Category(){}

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public long getCount(){
        return this.products.size();
    }

    public BigDecimal getAvg(){
        BigDecimal sum = this.products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal::add).orElse(BigDecimal.valueOf(0));


        return sum.divide(BigDecimal.valueOf(this.products.size()), 2, RoundingMode.DOWN );

    }

    public BigDecimal getSum(){
        return this.products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal::add).orElse(BigDecimal.valueOf(0));

    }
}
