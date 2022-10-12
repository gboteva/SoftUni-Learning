package christmas;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Bag {
    private String color;
    private int capacity;
    private List<Present> data;

    public Bag(String color, int capacity) {
        this.color = color;
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public String getColor() {
        return color;
    }

    public int getCapacity() {
        return capacity;
    }

    public int count(){
        return this.data.size();
    }

    public void add(Present present){
        if (this.data.size()<this.capacity){
            this.data.add(present);
        }
    }

    public boolean remove (String name){
        for (Present present : this.data ){
            if (present.getName().equals(name)){
                return this.data.remove(present);
            }
        }
        return false;
    }

    public Present heaviestPresent() {

        return this.data.stream()
                       .max(Comparator.comparingDouble(Present::getWeight)).orElse(null);
    }

    public Present getPresent(String name)  { //work
        for (Present present : this.data){
            if (present.getName().equals(name)){
                return present;
            }
        }
        return null;
    }

    public String report(){
        StringBuilder builder = new StringBuilder();
        //String colorWithUpperCase = this.color.substring(0,1).toUpperCase() + this.color.substring(1);

        builder.append(this.color + " bag contains:");
        for (Present present : this.data ){
           builder.append("\n").append(present);
        }
        return builder.toString().trim();
    }
}
