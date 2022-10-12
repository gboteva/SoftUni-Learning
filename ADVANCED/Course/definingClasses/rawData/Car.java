package definingClasses.rawData;

import java.util.Collection;
import java.util.Collections;

public class Car {
    private String model;
    private Engine engine;
    private Cargo cargo;
    private Collection<Tire> tires;

    public Car(String model, Engine engine, Cargo cargo, Collection<Tire> tires) {
        this.model = model;
        this.engine = engine;
        this.cargo = cargo;
        this.tires = tires;
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

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Collection<Tire> getTires() {
        return tires;
    }

    public void setTires(Collection<Tire> tires) {
        this.tires = tires;
    }


}
