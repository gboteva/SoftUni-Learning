package ex_p04_pizzaCalories;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private String name;
    private Dough dough;
    private List<Topping> toppings;

    public Pizza(String name, int numberOfToppings) {
        this.setName(name);
        this.setToppings(numberOfToppings);
    }

    private void setToppings (int count){
        if (count>=0 && count<=10){
            toppings = new ArrayList<>();
        }else {
            throw new IllegalArgumentException("Number of toppings should be in range [0..10].");
        }
    }

    private void  setName (String name){
        if (!name.trim().isEmpty() && name.trim().length()<=15){
            this.name = name;
        }else {
            throw new IllegalArgumentException("Pizza name should be between 1 and 15 symbols.");
        }
    }

    public void setDough (Dough dough){
        this.dough = dough;
    }

    public String getName() {
        return name;
    }

    public void addTopping (Topping topping){
        this.toppings.add(topping);
    }

    public double getOverallCalories (){
        return this.dough.calculateCalories() + this.toppings.stream().mapToDouble(e->e.calculateCalories()).sum();
    }
}
