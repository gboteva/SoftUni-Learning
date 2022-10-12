package definingClasses.carSalesman;

public class Car {
    private String model;
    private Engine engine;
    private String weight;
    private String color;

    public Car(String model, Engine engine) {
        this.model = model;
        this.engine = engine;
        this.weight = "n/a";
        this.color = "n/a";
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        //{CarModel}:
        //{EngineModel}:
        //Power: {EnginePower}
        //Displacement: {EngineDisplacement}
        //Efficiency: {EngineEfficiency}
        //Weight: {CarWeight}
        //Color: {CarColor}
        StringBuilder builder = new StringBuilder();
        builder.append(this.model).append(":").append(System.lineSeparator());
        builder.append(this.engine.getModel()).append(":").append(System.lineSeparator());
        builder.append("Power: ").append(this.engine.getPower()).append(System.lineSeparator());
        builder.append("Displacement: ").append(this.engine.getDisplacement()).append(System.lineSeparator());
        builder.append("Efficiency: ").append(this.engine.getEfficiency()).append(System.lineSeparator());
        builder.append("Weight: ").append(this.weight).append(System.lineSeparator());
        builder.append("Color: ").append(this.color);

        return builder.toString();
    }
}
