package exams.december2021.aquarium;

import java.util.ArrayList;
import java.util.List;

public class Aquarium {
    private String name;
    private int capacity;
    private int size;
    private List<Fish> fishInPool;

    public Aquarium(String name, int capacity, int size) {
        this.name = name;
        this.capacity = capacity;
        this.size = size;
        this.fishInPool = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSize() {
        return size;
    }

    public int getFishInPool(){
        return this.fishInPool.size();
    }

    public void add(Fish fish){
        boolean isThatName = false;
        for (Fish currentFish : this.fishInPool) {
            if (currentFish.getName().equals(fish.getName())){
                isThatName = true;
                break;
            }
        }
        if (!isThatName && getCapacity()>getFishInPool()){
            this.fishInPool.add(fish);
        }
    }

    public boolean remove(String name){
        for (Fish currentFish : this.fishInPool) {
            if (currentFish.getName().equals(name)){
                this.fishInPool.remove(currentFish);
                return true;
            }
        }
        return false;
    }

    public Fish findFish(String name){
        for (Fish currentFish : this.fishInPool) {
            if (currentFish.getName().equals(name)){
               return currentFish;
            }
        }
        return null;
    }

    public String report(){
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Aquarium: %s ^ Size: %d", getName(), getSize())).append("\n");
        for (Fish currentFish : this.fishInPool) {
          builder.append(currentFish).append("\n");
        }
        return builder.toString().trim();
    }
}
