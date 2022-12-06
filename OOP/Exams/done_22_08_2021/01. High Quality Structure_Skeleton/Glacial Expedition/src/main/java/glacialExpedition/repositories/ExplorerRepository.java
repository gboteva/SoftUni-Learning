package glacialExpedition.repositories;

import glacialExpedition.models.explorers.Explorer;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ExplorerRepository implements Repository<Explorer> {

    Map<String, Explorer> explorers;

    public ExplorerRepository() {
        this.explorers = new LinkedHashMap<>();
    }

    @Override
    public Collection<Explorer> getCollection() {
        return Collections.unmodifiableCollection(explorers.values());
    }

    @Override
    public void add(Explorer entity) {
        explorers.put(entity.getName(), entity);
    }

    @Override
    public boolean remove(Explorer entity) {
       if (explorers.containsKey(entity.getName())){
           explorers.remove(entity.getName());
           return true;
       }

       return false;
    }

    @Override
    public Explorer byName(String name) {
        return explorers.get(name);
    }
}
