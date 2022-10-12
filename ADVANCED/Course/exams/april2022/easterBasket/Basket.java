package exams.april2022.easterBasket;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Basket {
    private String material;
    private int capacity;
    private List<Egg> data; //may be list

    public Basket(String material, int capacity) {
        this.material = material;
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public void addEgg(Egg egg) {
        if (this.data.size() < this.capacity) {
            this.data.add(egg);
        }
    }

    public boolean removeEgg(String color) {
        for (Egg egg : this.data) {
            if (egg.getColor().equals(color)) {
                this.data.remove(egg);
                return true;
            }
        }
        return false;
    }

    public Egg getStrongestEgg() {

        Egg egg = this.data.stream()
                .sorted((f, s) -> Integer.compare(s.getStrength(), f.getStrength()))
                .limit(1)
                .collect(Collectors.toList())
                .get(0);
        return egg;

    }

    public Egg getEgg(String color){
        Egg egg = this.data.stream()
                .filter(e->e.getColor().equals(color))
                .findFirst()
                .get();
        return egg;
    }

    public int getCount(){
        return this.data.size();
    }

    public String report(){
        StringBuilder builder = new StringBuilder();
        builder.append(this.material).append(" basket contains:");
        builder.append("\n");
        for (Egg egg : this.data) {
           builder.append(egg).append("\n");
        }
        return builder.toString().trim();
    }

}
