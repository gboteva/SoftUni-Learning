package christmasRaces.repositories;

import christmasRaces.entities.cars.Car;
import christmasRaces.repositories.interfaces.Repository;

import java.util.*;
import java.util.stream.Collectors;

public class CarRepository implements Repository<Car> {

    Map<String, Car> models;

    public CarRepository() {
        this.models = new LinkedHashMap<>();
    }

    @Override
    public Car getByName(String name) {
        return this.models.get(name);
    }

    @Override
    public Collection<Car> getAll() {
        return Collections.unmodifiableCollection(new ArrayList<>(this.models.values()));
    }

    @Override
    public void add(Car model) {
        this.models.put(model.getModel(), model);
    }

    @Override
    public boolean remove(Car model) {
        if (!this.models.containsKey(model.getModel())) {
            return false;
        }

        this.models.remove(model.getModel());
        return true;
    }
}
