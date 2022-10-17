package exams.february2022.parrots;

import java.util.ArrayList;
import java.util.List;

public class Cage {
    private String name;
    private int capacity;
    private List<Parrot> data;

    public Cage(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void add (Parrot parrot){
        if (this.data.size()<getCapacity()){
            this.data.add(parrot);
        }
    }

    public boolean remove (String name){
        for (Parrot p : this.data) {
            if (p.getName().equals(name)){
                this.data.remove(p);
                return true;
            }
        }
        return false;
    }

    public Parrot sellParrot(String name) {
        for (Parrot p : this.data) {
            if (p.getName().equals(name)){
                p.setAvailable(false);
                return p;
            }
        }
        return null;
    }

    public List<Parrot> sellParrotBySpecies(String species){
        List<Parrot> list = new ArrayList<>();
        for (Parrot p : this.data) {
            if (p.getSpecies().equals(species)){
                p.setAvailable(false);
                list.add(p);
            }
        }
        return list;
    }

    public int count(){
        return this.data.size();
    }

    public String report(){
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Parrots available at %s:", getName())).append("\n");
        this.data.stream()
                .filter(Parrot::isAvailable)
                .forEach(p-> builder.append(p).append("\n"));

        return builder.toString().trim();
    }
}
