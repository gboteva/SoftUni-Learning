package ex_p03_WildFarm;

import java.text.DecimalFormat;

public class Cat extends Felime{
    private String breed;

    public Cat(String animalName, String animalType, Double animalWeight, Integer foodEaten, String livingRegion, String breed) {
        super(animalName, animalType, animalWeight, foodEaten, livingRegion);
        this.breed = breed;
    }

    protected String getBreed() {
        return breed;
    }

    @Override
    void makeSound() {
        System.out.println("Meowwww");
    }


    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#######.##");
        return String.format("%s[%s, %s, %s, %s, %d]",
                getAnimalType(), getAnimalName(), getBreed(), df.format(getAnimalWeight()), getLivingRegion(), getFoodEaten());
    }
}
