package FundamentalsCourse.objectsAndClasses_6.MoreEx.RawData_02;

import java.util.List;

public class Car {
    private String model;
    Engine engine;
    Cargo cargo;
    List<Tire> tires;

    public Car (String model, Engine engine, Cargo cargo, List<Tire> tires){
        this.model = model;
        this.engine = engine;
        this.cargo = cargo;
        this.tires = tires;
    }

    public List<Tire> getTires(){
     return this.tires;
    }

    public Engine getEngine(){
        return this.engine;
    }

    public Cargo getCargo(){
        return this.cargo;
    }

    public String getModel(){
        return this.model;
    }
}
