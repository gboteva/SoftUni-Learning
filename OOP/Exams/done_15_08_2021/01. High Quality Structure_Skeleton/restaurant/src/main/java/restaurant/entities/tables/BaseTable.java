package restaurant.entities.tables;

import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.tables.interfaces.Table;

import java.util.ArrayList;
import java.util.Collection;

import static restaurant.common.ExceptionMessages.INVALID_NUMBER_OF_PEOPLE;
import static restaurant.common.ExceptionMessages.INVALID_TABLE_SIZE;

public abstract class BaseTable implements Table {
    private Collection<HealthyFood> healthyFood;
    private Collection<Beverages> beverages;
    private int number;
    private int size;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReservedTable;
    private double allPeople;

    protected BaseTable(int number, int size, double pricePerPerson) {
        this.number = number;
        setSize(size);
        this.pricePerPerson = pricePerPerson;
        this.healthyFood = new ArrayList<>();
        this.beverages = new ArrayList<>();
        isReservedTable = false;
    }

    private void setSize(int size) {
        if (size < 0){
            throw new IllegalArgumentException(INVALID_TABLE_SIZE);
        }
        this.size = size;
    }

    private void setNumberOfPeople(int numberOfPeople) {
        if (numberOfPeople <= 0){
            throw new IllegalArgumentException(INVALID_NUMBER_OF_PEOPLE);
        }
        this.numberOfPeople = numberOfPeople;
    }

    @Override
    public int getTableNumber() {
        return this.number;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public int numberOfPeople() {
        return this.numberOfPeople;
    }

    @Override
    public double pricePerPerson() {
        return this.pricePerPerson;
    }

    @Override
    public boolean isReservedTable() {
        return this.isReservedTable;
    }

    @Override
    public double allPeople() {
        this.allPeople = pricePerPerson() * numberOfPeople();
        return this.allPeople;
    }

    @Override
    public void reserve(int numberOfPeople) {
        isReservedTable = true;
        setNumberOfPeople(numberOfPeople);
    }

    @Override
    public void orderHealthy(HealthyFood food) {
        this.healthyFood.add(food);
    }

    @Override
    public void orderBeverages(Beverages beverages) {
        this.beverages.add(beverages);
    }

    @Override
    public double bill() {
        double priceOfAllFood = healthyFood.stream().mapToDouble(HealthyFood::getPrice).sum();
        double priceOfAllBeverages = beverages.stream().mapToDouble(Beverages::getPrice).sum();
        return priceOfAllFood + priceOfAllBeverages + allPeople();
    }

    @Override
    public void clear() {
        this.beverages.clear();
        this.healthyFood.clear();
        isReservedTable = false;
        this.numberOfPeople = 0;
        this.allPeople = 0; //? why ->sets the count of people and price to 0
    }

    @Override
    public String tableInformation() {
       StringBuilder builder = new StringBuilder();
       builder.append("Table - ").append(getTableNumber()).append(System.lineSeparator());
       builder.append("Size - ").append(getSize()).append(System.lineSeparator());
       builder.append("Type - ").append(getClass().getSimpleName()).append(System.lineSeparator());
       builder.append("All price - ").append(pricePerPerson());

       return builder.toString().trim();
    }
}
