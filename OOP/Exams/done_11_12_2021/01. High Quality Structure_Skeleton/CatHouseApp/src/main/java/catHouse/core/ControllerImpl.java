package catHouse.core;

import catHouse.entities.cat.Cat;
import catHouse.entities.cat.LonghairCat;
import catHouse.entities.cat.ShorthairCat;
import catHouse.entities.houses.House;
import catHouse.entities.houses.LongHouse;
import catHouse.entities.houses.ShortHouse;
import catHouse.entities.toys.Ball;
import catHouse.entities.toys.Mouse;
import catHouse.entities.toys.Toy;
import catHouse.repositories.ToyRepository;

import java.util.LinkedHashMap;
import java.util.Map;

import static catHouse.common.ConstantMessages.*;
import static catHouse.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private ToyRepository toys;
    private Map<String, House> houses;

    public ControllerImpl() {
        this.toys = new ToyRepository();
        this.houses = new LinkedHashMap<>();
    }

    @Override
    public String addHouse(String type, String name) {

        House house;

        if ("ShortHouse".equals(type)){
            house = new ShortHouse(name);
        }else if ("LongHouse".equals(type)){
            house = new LongHouse(name);
        }else {
            throw new NullPointerException(INVALID_HOUSE_TYPE);
        }

        this.houses.put(name, house);

        return String.format(SUCCESSFULLY_ADDED_HOUSE_TYPE, type);
    }

    @Override
    public String buyToy(String type) {

        Toy toy;
        if ("Ball".equals(type)){
            toy = new Ball();
        }else if ("Mouse".equals(type)){
            toy = new Mouse();
        }else {
            throw new IllegalArgumentException(INVALID_TOY_TYPE);
        }

        this.toys.buyToy(toy);

        return String.format(SUCCESSFULLY_ADDED_TOY_TYPE, type);
    }

    @Override
    public String toyForHouse(String houseName, String toyType) {

        Toy toyToBuy = this.toys.findFirst(toyType);
        if (toyToBuy == null){
            throw new IllegalArgumentException(String.format(NO_TOY_FOUND,toyType));
        }

        House house = this.houses.get(houseName);
        house.buyToy(toyToBuy);
        this.toys.removeToy(toyToBuy);

        return String.format(SUCCESSFULLY_ADDED_TOY_IN_HOUSE, toyType,houseName);
    }

    @Override
    public String addCat(String houseName, String catType, String catName, String catBreed, double price) {
        Cat cat;

        if ("ShorthairCat".equals(catType)){
            cat = new ShorthairCat(catName, catBreed, price);
        }else if ("LonghairCat".equals(catType)){
            cat = new LonghairCat(catName, catBreed, price);
        }else {
            throw new IllegalArgumentException(INVALID_CAT_TYPE);
        }

        House house = this.houses.get(houseName);

        boolean isValidLong = "LongHouse".equals(house.getClass().getSimpleName()) && catType.startsWith("Long");
        boolean isValidShort = "ShortHouse".equals(house.getClass().getSimpleName()) && catType.startsWith("Short");

        if (isValidLong || isValidShort){
            house.addCat(cat);
            return String.format(SUCCESSFULLY_ADDED_CAT_IN_HOUSE, catType, houseName);
        }else {
            return UNSUITABLE_HOUSE;
        }
    }

    @Override
    public String feedingCat(String houseName) {
        this.houses.get(houseName).feeding();
        return String.format(FEEDING_CAT, houses.get(houseName).getCats().size());
    }

    @Override
    public String sumOfAll(String houseName) {
        House house = houses.get(houseName);
        double sumOfToys = house.getToys().stream().mapToDouble(Toy::getPrice).sum();
        double sumOfCats = house.getCats().stream().mapToDouble(Cat::getPrice).sum();

        return String.format(VALUE_HOUSE, houseName, (sumOfCats + sumOfToys));
    }

    @Override
    public String getStatistics() {
        StringBuilder builder = new StringBuilder();
        for (House house : houses.values()) {
            builder.append(house.getStatistics()).append(System.lineSeparator());
        }

        return builder.toString().trim();
    }
}
