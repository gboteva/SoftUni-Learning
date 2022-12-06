package easterRaces.repositories;

import easterRaces.entities.cars.Car;
import easterRaces.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class CarRepository implements Repository<Car> {

    private Map<String, Car> models;

    public CarRepository() {
        this.models = new LinkedHashMap<>();
    }

    @Override
    public Car getByName(String name) {
        return this.models.values().stream()
                .filter(m->m.getModel().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Collection<Car> getAll() {
        return Collections.unmodifiableCollection(this.models.values());
    }

    @Override
    public void add(Car model) {
        this.models.put(model.getModel(), model);
    }

    @Override
    public boolean remove(Car model) {
        Car removed = this.models.remove(model.getModel());

        if (removed == null){
            return false;
        }

        return true;
    }
}
