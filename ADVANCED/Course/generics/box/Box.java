package generics.box;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Box <T extends Comparable<T>>{
    private List<T> value;

    public Box() {
        this.value = new ArrayList<>();
    }

    public void add (T element){
        this.value.add(element);
    }

    public void swap(int firstIndex, int secondIndex){
        Collections.swap(value,firstIndex,secondIndex);
    }

    public int count (T elementToCompare){
        return (int) value.stream().filter(el-> el.compareTo(elementToCompare)>0).count();
    }

    public void print(){
        this.value.forEach(v-> System.out.println(v.getClass().getName() + ": " + v.toString()));
    }

    @Override
    public String toString() {
        return String.format("%s: %s", value.getClass().getName(), value.toString());
    }
}
