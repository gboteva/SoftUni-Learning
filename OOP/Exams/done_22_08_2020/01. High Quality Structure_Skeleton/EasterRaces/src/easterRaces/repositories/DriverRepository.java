package easterRaces.repositories;

import easterRaces.entities.drivers.Driver;
import easterRaces.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class DriverRepository implements Repository<Driver> {

    private Map<String, Driver> models;

    public DriverRepository() {
        this.models = new LinkedHashMap<>();
    }

    @Override
    public Driver getByName(String name) {
        return this.models.values().stream()
                .filter(d->d.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Collection<Driver> getAll() {
        return Collections.unmodifiableCollection(this.models.values());
    }

    @Override
    public void add(Driver model) {
        this.models.put(model.getName(), model);
    }

    @Override
    public boolean remove(Driver model) {
        Driver removed = this.models.remove(model.getName());

        if (removed == null){
            return false;
        }

        return true;
    }
}
