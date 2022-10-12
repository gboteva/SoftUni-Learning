package FundamentalsCourse.objectsAndClasses_6.MoreEx.CarSealsman_03;

public class Car {
    private String model;
    Engine engine;
    private String weight;
    private String color;

    public Car(String model, Engine engine){
        this.model = model;
        this.engine = engine;
        this.weight = "n/a";
        this.color = "n/a";
    }

    public void setWeight(String weight){
        this.weight = weight;
    }

    public void setColor(String color){
        this.color = color;
    }


    public String toString(){
        return String.format("%s:%n  %s:%n    Power: %d%n    Displacement: %s%n    Efficiency: %s%n" +
                "  Weight: %s%n  Color: %s",
                this.model, this.engine.getModel(), this.engine.getPower(), this.engine.getDisplacement(), this.engine.getEfficiency(),
                this.weight, this.color);
    }
}
