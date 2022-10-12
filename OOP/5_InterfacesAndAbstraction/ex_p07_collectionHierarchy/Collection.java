package ex_p07_collectionHierarchy;

import java.util.ArrayList;
import java.util.List;

public abstract class Collection extends ArrayList {
    private static final int maxSize = 100;
    private List<String> items;

    public Collection() {
        this.items = new ArrayList<>();
    }

    public List<String> getItems() {
        return items;
    }
}
