package exams.august2022.sanctuary;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Habitat {
    private int capacity;
    private List<Elephant> data;

    public Habitat(int capacity) {
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Elephant> getData() {
        return this.data;
    }

    public void setData(List<Elephant> data) {
        this.data = data;
    }

    public void add(Elephant elephant) {
        if (getData().size() < getCapacity()) {
            getData().add(elephant);
        }
    }

    public boolean remove(String name) {
        for (Elephant elephant : this.data) {
            if (elephant.getName().equals(name)){
                this.data.remove(elephant);
                return true;
            }
        }
       return false;
    }

    public Elephant getElephant(String retiredFrom) {
        for (Elephant elephant : this.data) {
            if (elephant.getRetiredFrom().equals(retiredFrom)) {
                return elephant;
            }
        }
        return null;
    }

    public Elephant getOldestElephant() {
        return this.data.stream().max(Comparator.comparing(Elephant::getAge)).get();
    }

    public int getAllElephants() {
        return this.data.size();
    }

    public String getReport() {
        StringBuilder builder = new StringBuilder();
        builder.append("Saved elephants in the park:").append("\n");
        for (Elephant elephant : this.data) {
            builder.append(String.format("%s came from: %s", elephant.getName(), elephant.getRetiredFrom()))
                    .append("\n");
        }

        return builder.toString().trim();
    }

}
