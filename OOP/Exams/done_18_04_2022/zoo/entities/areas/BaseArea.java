package zoo.entities.areas;

import zoo.entities.animals.Animal;
import zoo.entities.foods.Food;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static zoo.core.common.ExceptionMessages.AREA_NAME_NULL_OR_EMPTY;
import static zoo.core.common.ExceptionMessages.NOT_ENOUGH_CAPACITY;

public abstract class BaseArea implements Area {

    private String name;
    private int capacity;

    private Collection<Food> foods;
    private Collection<Animal> animals;

    protected BaseArea(String name, int capacity) {
        setName(name);
        this.capacity = capacity;
        this.foods = new ArrayList<>();
        this.animals = new ArrayList<>();
    }

    private void setName(String name){
        if (name == null || name.trim().isEmpty()){
            throw new NullPointerException(AREA_NAME_NULL_OR_EMPTY);
        }

        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Collection<Animal> getAnimals() {
        return this.animals;
    }

    @Override
    public Collection<Food> getFoods() {
        return this.foods;
    }

    @Override
    public int sumCalories() {
        return this.foods.stream()
                .mapToInt(Food::getCalories).sum();
    }

    @Override
    public void addAnimal(Animal animal) {
        if (this.animals.size() == this.capacity){
            throw new IllegalStateException(NOT_ENOUGH_CAPACITY);
        }

        this.animals.add(animal);
    }

    @Override
    public void removeAnimal(Animal animal) {
        this.animals.remove(animal);
    }

    @Override
    public void addFood(Food food) {
        this.foods.add(food);
    }

    @Override
    public void feed() {
        this.animals.forEach(Animal::eat);
    }

    @Override
    public String getInfo() {
        StringBuilder builder = new StringBuilder();

        builder.append(String.format("%s (%s):", name, getClass().getSimpleName())).append(System.lineSeparator());

        String string;
        if (animals.isEmpty()){
            string = "none";
        }else {
            string = animals.stream().map(Animal::getName).collect(Collectors.joining(" "));
        }
        builder.append("Animals: ").append(string).append(System.lineSeparator());
        builder.append("Foods: ").append(foods.size()).append(System.lineSeparator());
        builder.append("Calories: ").append(sumCalories());

        return builder.toString().trim();

    }
}
