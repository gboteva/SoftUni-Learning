package generics.customList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class CustomList <T extends Comparable<T>> implements Iterable<T> {
    private List<T> values;

    public CustomList() {
        this.values = new ArrayList<>();
    }

    public void add (T element){
        this.values.add(element);
    }

    public T remove (int index){
        return this.values.remove(index);
    }

    public boolean contains (T element){
        return this.values.contains(element);
    }

    public void swap (int firstIndex, int secondIndex){
        Collections.swap(this.values, firstIndex, secondIndex);
    }

    public int countGreaterThan (T element){
        return (int) this.values.stream().filter(el->el.compareTo(element)>0).count();
    }

    public T getMax(){
       return Collections.max(this.values);
    }

    public T getMin(){
     return Collections.min(this.values);
    }

    public void print(){
        this.values.forEach(System.out::println);
    }

    public int size(){
        return this.values.size();
    }

    public void sort (){
        Collections.sort(this.values);
    }

    public T get(int index){
        return this.values.get(index);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;
            @Override
            public boolean hasNext() {
                if (index < values.size()){
                    return true;
                }
                return false;
            }

            @Override
            public T next() {
                return values.get(index++);
            }
        };
    }
}
