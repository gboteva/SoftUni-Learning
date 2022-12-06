package christmasRaces.repositories;

import christmasRaces.entities.drivers.Driver;
import christmasRaces.repositories.interfaces.Repository;

import java.util.*;

public class DriverRepository implements Repository<Driver> {

    Map<String, Driver> models;

    public DriverRepository() {
        this.models = new LinkedHashMap<>();
    }

    @Override
    public Driver getByName(String name) {
        return this.models.get(name);
    }

    @Override
    public Collection<Driver> getAll() {
        return Collections.unmodifiableCollection(new ArrayList<>(this.models.values()));
    }

    @Override
    public void add(Driver model) {
        this.models.put(model.getName(), model);
    }

    @Override
    public boolean remove(Driver model) {
        if (!this.models.containsKey(model.getName())) {
            return false;
        }

        this.models.remove(model.getName());
        return true;
    }
}
