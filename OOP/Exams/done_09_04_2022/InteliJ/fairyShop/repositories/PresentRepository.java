package fairyShop.repositories;

import fairyShop.models.Present;

import java.util.*;

public class PresentRepository implements Repository<Present> {

    private Map<String, Present> presents;

    public PresentRepository() {
        this.presents = new LinkedHashMap<>();
    }

    @Override
    public Collection<Present> getModels() {
        return Collections.unmodifiableCollection(new ArrayList<>(this.presents.values()));
    }

    @Override
    public void add(Present model) {
        this.presents.put(model.getName(), model);
    }

    @Override
    public boolean remove(Present model) {
        if (this.presents.containsKey(model.getName())){
            this.presents.remove(model.getName());
            return true;
        }
        return false;
    }

    @Override
    public Present findByName(String name) {
        return this.presents.get(name);
    }
}
