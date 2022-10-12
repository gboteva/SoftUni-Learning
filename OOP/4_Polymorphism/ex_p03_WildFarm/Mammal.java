package ex_p03_WildFarm;

import java.text.DecimalFormat;

public abstract class Mammal extends Animal {
    private String livingRegion;

    public Mammal(String animalName, String animalType, Double animalWeight, Integer foodEaten, String livingRegion) {
        super(animalName, animalType, animalWeight, foodEaten);
        this.livingRegion = livingRegion;
    }

    protected String getLivingRegion() {
        return livingRegion;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#######.##");
        return String.format("%s[%s, %s, %s, %d]"
                , getAnimalType(), getAnimalName(), df.format(getAnimalWeight()), this.livingRegion, getFoodEaten());
    }

    @Override
    public void eat(Food food) {
        switch (getAnimalType()) {
            case "Mouse":
                if (food.getClass().getSimpleName().equals("Vegetable")){
                    super.setFoodEaten(getFoodEaten() + food.getQuantity());
                }else {
                    System.out.printf("Mice are not eating that type of food!%n");
                }
                break;
            case "Zebra":
                if (food.getClass().getSimpleName().equals("Vegetable")){
                    super.setFoodEaten(getFoodEaten() + food.getQuantity());
                }else {
                    System.out.printf("Zebras are not eating that type of food!%n");
                }
                break;
            case "Cat":
                super.setFoodEaten(getFoodEaten() + food.getQuantity());
                break;
            case "Tiger":
                if (food.getClass().getSimpleName().equals("Meat")){
                    super.setFoodEaten(getFoodEaten() + food.getQuantity());
                }else {
                    System.out.printf("Tigers are not eating that type of food!%n");
                }
                break;
        }
    }
}
