package easterRaces.repositories;

import easterRaces.entities.racers.Race;
import easterRaces.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class RaceRepository implements Repository<Race> {

    private Map<String, Race> models;

    public RaceRepository() {
        this.models = new LinkedHashMap<>();
    }

    @Override
    public Race getByName(String name) {
        return this.models.values().stream()
                .filter(r->r.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Collection<Race> getAll() {
        return Collections.unmodifiableCollection(this.models.values());
    }

    @Override
    public void add(Race model) {
        this.models.put(model.getName(), model);
    }

    @Override
    public boolean remove(Race model) {
        Race removed = this.models.remove(model.getName());

        if (removed == null){
            return false;
        }

        return true;
    }
}
