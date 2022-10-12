package FundamentalsCourse.objectsAndClasses_6.MoreEx.CarSealsman_03;

public class Engine {
    private String model;
    private int power;
    private String displacement;
    private String efficiency;

    public Engine(String model, int power){
        this.model = model;
        this.power = power;
        this.displacement = "n/a";
        this.efficiency = "n/a";
    }

    public void setDisplacement(String displacement){
        this.displacement = displacement;
    }

    public void setEfficiency (String efficiency){
        this.efficiency = efficiency;
    }

    public String getModel(){
        return this.model;
    }

    public int getPower(){
        return this.power;
    }

    public String getDisplacement(){
        return this.displacement;
    }

    public String getEfficiency(){
        return this.efficiency;
    }
}
