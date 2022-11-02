package pizzaCalories;

public class Topping {
    private String toppingType;
    private double weight;

    public Topping(String toppingType, double weight) {
        setToppingType(toppingType);
        setWeight(weight);
    }

    private void setToppingType(String toppingType) {
        if (!toppingType.equals("Meat") && !toppingType.equals("Veggies")
        && !toppingType.equals("Cheese") && !toppingType.equals("Sauce")){
            throw new IllegalArgumentException("Cannot place " + toppingType + " on top of your pizza.");
        }

        this.toppingType = toppingType;
    }

    private void setWeight(double weight) {
        if (weight < 1 || weight > 50){
            throw new IllegalArgumentException(toppingType + " weight should be in the range [1..50].");
        }

        this.weight = weight;
    }

    public double calculateCalories(){
        return 2 * this.weight * getModifier(this.toppingType);
    }

    private double getModifier (String toppingType){
        switch (toppingType){
            case "Meat": return 1.2;
            case "Veggies": return 0.8;
            case "Cheese": return 1.1;
            case "Sauce": return 0.9;
        }
        return 0;
    }
}
