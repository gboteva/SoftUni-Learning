package genericBox;

import java.util.ArrayList;
import java.util.List;

public class Box <T extends Comparable<T>> {
    private List<T> items;

    public Box (){
        this.items = new ArrayList<>();
    }


    public void add (T item){
        this.items.add(item);
    }

    public String toString (){
        StringBuilder builder = new StringBuilder();
        for (T item : this.items ){
            builder.append(item.getClass().getName()).append(": ").append(item).append("\n");
        }
       return builder.toString();
    }

    public void swap (int from, int to){
        T firstElement = this.items.get(from);
        T secondElement = this.items.get(to);
        this.items.set(from, secondElement);
        this.items.set(to, firstElement);
    }

    public int graterThan (T value){
        int count = 0;
        for (T item : this.items ){
            if (item.compareTo(value)>0){
                count++;
            }
        }
        return count;
    }
}
