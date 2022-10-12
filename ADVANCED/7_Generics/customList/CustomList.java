package customList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class CustomList<T extends Comparable<T>> implements Iterable<T> {
    private List<T> items;

    public CustomList() {
        this.items = new ArrayList<>();
    }

    public void add(T element) {
        this.items.add(element);
    }

    public T remove(int index) {
        return this.items.remove(index);
    }

    public boolean contains(T element) {
        return this.items.contains(element);
    }

    public void swap(int index1, int index2) {
        T firstElement = this.items.get(index1);
        T secondElement = this.items.get(index2);
        this.items.set(index1, secondElement);
        this.items.set(index2, firstElement);
    }

    public int countGreaterThan(T element) {
        int count = 0;
        for (T item : this.items) {
            if (item.compareTo(element) > 0) {
                count++;
            }
        }
        return count;
    }

    public T getMax() {
        return Collections.max(this.items);
    }

    public T getMin() {
        return Collections.min(this.items);
    }

    public void print() {
////        StringBuilder builder = new StringBuilder();
////        for (T item : this.items) {
////            builder.append(item).append("\n");
////        }
////        System.out.print(builder);
        Iterator<T> it = this.iterator();
        it.forEachRemaining(a->{
            if (a!=null){
                System.out.println(a);
            }
        });
    }

    public void sort() {
        Collections.sort(this.items);
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index;

            @Override
            public boolean hasNext() {
                return index < items.size();
            }

            @Override
            public T next() {
                T element = items.get(index);
                index++;
                return element;
            }
        };
    }
}




