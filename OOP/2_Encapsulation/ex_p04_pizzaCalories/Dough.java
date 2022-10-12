package ex_p04_pizzaCalories;

public class Dough {
    private String flourType;
    private String bakingTechnique;
    private double weight;

    public Dough(String flourType, String bakingTechnique, double weight) {
        this.setFlourType(flourType);
        this.setBakingTechnique(bakingTechnique);
        this.setWeight(weight);
    }

    private void setWeight(double weight){
        if (weight>=1 && weight<=200){
            this.weight = weight;
        }else {
            throw new IllegalArgumentException("Dough weight should be in the range [1..200].");
        }
    }

    private void setFlourType (String flourType){
        if (flourType.equals("White") || flourType.equals("Wholegrain")){
            this.flourType = flourType;
        }else {
            throw new IllegalArgumentException("Invalid type of dough.");
        }
    }

    private void setBakingTechnique (String bakingTechnique){
        switch (bakingTechnique){
            case "Crispy":
            case "Chewy":
            case "Homemade":
                this.bakingTechnique = bakingTechnique;
                break;
            default: throw new IllegalArgumentException("Invalid type of dough.");
        }
    }

    public double calculateCalories (){
        double typeModifier = 0;
        double techniqueModifier = 0;

        if (this.flourType.equals("White")){
            typeModifier = 1.5;
        }else if (this.flourType.equals("Wholegrain")){
            typeModifier = 1;
        }

        switch (this.bakingTechnique){
            case "Crispy":
                techniqueModifier = 0.9;
                break;
            case "Chewy":
                techniqueModifier = 1.1;
                break;
            case "Homemade":
                techniqueModifier = 1;
                break;
        }

        return (2*this.weight) * typeModifier * techniqueModifier;
    }
}
