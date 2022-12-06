package restaurant.repositories;


import restaurant.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class BaseRepository<T> implements Repository<T> {

    private Collection<T> entities;

    protected BaseRepository() {
        this.entities = new ArrayList<>();
    }

    @Override
    public Collection<T> getAllEntities() {
        return Collections.unmodifiableCollection(entities);
    }

    @Override
    public void add(T entity) {
        this.entities.add(entity);
    }
}
