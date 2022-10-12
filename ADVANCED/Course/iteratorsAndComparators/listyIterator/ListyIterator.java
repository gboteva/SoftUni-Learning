package iteratorsAndComparators.comparingObjects.listyIterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class ListyIterator implements Iterable<String> {
    private List<String> collection;
    private int index;

    public void create(String... elements) {
        if (elements.length == 0) {
            this.collection = new ArrayList<>();
        } else {
            this.collection = Arrays.asList(elements);
        }
        index = 0;
    }

    public boolean move() {
        if (index + 1 < this.collection.size()) {
            index++;
            return true;
        }
        return false;
    }

    public boolean hasNext() {
        if (index + 1 < this.collection.size()) {
            return true;
        }
        return false;
    }

    public void print() {
        System.out.println(collection.get(index));
    }

    public void printAll() {
        Iterator<String> iterator = iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < collection.size();
            }

            @Override
            public String next() {
                return collection.get(currentIndex++);
            }
        };
    }
}
