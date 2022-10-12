package ex_p03_WildFarm;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();
        int lineCounter = 0;
        List<Mammal> animals = new ArrayList<>();
        List<Food> foods = new ArrayList<>();

        Mammal mammal;
        Food food;

        while (!line.equals("End")) {

            if (lineCounter % 2 == 0) {
                String[] animalInfo = line.split("\\s+");
                mammal = creatNewAnimal(animalInfo);
                animals.add(mammal);

            } else {
                String[] foodInfo = line.split("\\s+");
                String foodType = foodInfo[0];
                Integer foodQuantity = Integer.parseInt(foodInfo[1]);

                switch (foodType) {
                    case "Vegetable":
                        food = new Vegetable(foodQuantity);
                        foods.add(food);
                        break;
                    case "Meat":
                        food = new Meat(foodQuantity);
                        foods.add(food);
                        break;
                }
            }

            line = scanner.nextLine();
            lineCounter++;
        }
        for (int i = 0; i < animals.size(); i++) {
            animals.get(i).makeSound();
            animals.get(i).eat(foods.get(i));
        }
        animals.forEach(System.out::println);

    }

    private static Mammal creatNewAnimal(String[] animalInfo) {
        String animalType = animalInfo[0];
        String animalName = animalInfo[1];
        Double animalWeight = Double.parseDouble(animalInfo[2]);
        String animalLivingRegion = animalInfo[3];

        if (animalType.equals("Cat")) {
            String catBreed = animalInfo[4];
            return new Cat(animalName, animalType, animalWeight, 0, animalLivingRegion, catBreed);
        }

        switch (animalType) {
            case "Mouse":
                return new Mouse(animalName, animalType, animalWeight, 0, animalLivingRegion);

            case "Zebra":
                return new Zebra(animalName, animalType, animalWeight, 0, animalLivingRegion);

            case "Tiger":
                return new Tiger(animalName, animalType, animalWeight, 0, animalLivingRegion);

        }
        return null;
    }
}
