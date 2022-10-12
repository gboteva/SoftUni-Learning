package beforeCourse.objectsAndClasses_6.ex.VehicleCatalogue_06;

public class Vehicle {
    private String type;
    private String model;
    private String color;
    private int horsePower;

    public Vehicle(String type, String model, String color, int horsePower) {
        type = type.substring(0,1).toUpperCase() + type.substring(1);
        this.type = type;
        this.model = model;
        this.color = color;
        this.horsePower = horsePower;
    }

    public String getModel() {
        return this.model;
    }

    public String getType(){
        return this.type;
    }

    public int getHorsePower() {
        return horsePower;
    }

    @Override
    public String toString() {
        return String.format("Type: %s%n" +
                "Model: %s%n" +
                "Color: %s%n" +
                "Horsepower: %d", this.type, this.model, this.color, this.horsePower);
        //за да направим типа с главни букви: getType().toUpperCase().charAt(0) + getType().substring(1);
    }
}

