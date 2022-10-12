package ex_p03_WildFarm;

public class Tiger extends Felime{

    public Tiger(String animalName, String animalType, Double animalWeight, Integer foodEaten, String livingRegion) {
        super(animalName, animalType, animalWeight, foodEaten, livingRegion);

    }

    @Override
    void makeSound() {
        System.out.println("ROAAR!!!");
    }

}
