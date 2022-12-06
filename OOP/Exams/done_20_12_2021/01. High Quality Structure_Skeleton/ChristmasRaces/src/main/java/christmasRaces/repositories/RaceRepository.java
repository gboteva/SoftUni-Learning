package christmasRaces.repositories;

import christmasRaces.entities.races.Race;
import christmasRaces.repositories.interfaces.Repository;

import java.util.*;

public class RaceRepository implements Repository<Race> {

    Map<String, Race> models;

    public RaceRepository() {
        this.models = new LinkedHashMap<>();
    }

    @Override
    public Race getByName(String name) {
        return this.models.get(name);
    }

    @Override
    public Collection<Race> getAll() {
        return Collections.unmodifiableCollection(new ArrayList<>(this.models.values()));
    }

    @Override
    public void add(Race model) {
        this.models.put(model.getName(), model);
    }

    @Override
    public boolean remove(Race model) {
        if (!this.models.containsKey(model.getName())) {
            return false;
        }

        this.models.remove(model.getName());
        return true;
    }
}
