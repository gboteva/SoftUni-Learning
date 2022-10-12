package DefiningClasses.ex.P05_CarSalesman;

public class Engine {
    private String model;
    private int power;
    private String displacement;
    private String efficiency;

    public Engine (String model, int power){
        this.model = model;
        this.power = power;
        displacement = "n/a";
        efficiency = "n/a";
    }

   public Engine (String model, int power, String displacement, String efficiency){
        this(model,power);
        this.displacement = displacement;
        this.efficiency = efficiency;
   }

   public void setDisplacement (String displacement){
        this.displacement = displacement;
   }

   public void  setEfficiency (String efficiency){
        this.efficiency = efficiency;
   }

   public String getModel(){
        return this.model;
   }

    public int getPower() {
        return power;
    }

    public String getDisplacement() {
        return displacement;
    }

    public String getEfficiency() {
        return efficiency;
    }
}
