package viceCity.repositories;

import viceCity.models.guns.Gun;
import viceCity.repositories.interfaces.Repository;

import java.util.*;

public class GunRepository implements Repository<Gun> {

    private Collection<Gun> models;

    public GunRepository() {
        this.models = new ArrayList<>();
    }

    @Override
    public Collection<Gun> getModels() {
        return Collections.unmodifiableCollection(models);
    }

    @Override
    public void add(Gun model) {
        if (!models.contains(model)){
            models.add(model);
        }
    }

    @Override
    public boolean remove(Gun model) {
        if (!models.contains(model)){
            return false;
        }

        this.models.remove(model);
        return true;
    }

    @Override
    public Gun find(String name) {
        return models.stream().filter(g->g.getName().equals(name))
                .findFirst().orElse(null);
    }
}
