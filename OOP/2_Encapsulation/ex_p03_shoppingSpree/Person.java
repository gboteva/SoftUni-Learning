package shoppingSpree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Person {
    private String name;
    private double money;
    private List<Product> products;

    public Person(String name, double money) {
        setName(name);
        setMoney(money);
        this.products = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        this.name = name;
    }

    private void setMoney(double money) {
        if (money < 0) {
            throw new IllegalArgumentException("Money cannot be negative");
        }

        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void buyProduct(Product product) {
        if (this.money < product.getCost()) {
            throw new IllegalArgumentException(String.format("%s can't afford %s", this.name, product.getName()));
        }
        this.products.add(product);
        this.money -= product.getCost();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (this.products.isEmpty()) {
            builder.append(String.format("%s - Nothing bought", this.name));
        } else {
            String str = products.stream().map(Product::getName).collect(Collectors.joining(", "));
            builder.append(this.name).append(" bought ").append(str).append(System.lineSeparator());
        }

        return builder.toString().trim();
    }
}
