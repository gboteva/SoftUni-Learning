package exams.june_2021.groomingSalon;

import java.util.ArrayList;
import java.util.List;

public class GroomingSalon {
    private int capacity;
    private List<Pet> data;

    public GroomingSalon(int capacity) {
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public void add (Pet pet){
        if (this.data.size()<capacity){
            this.data.add(pet);
        }
    }

    public boolean remove (String name){
        for (Pet pet : this.data) {
            if (pet.getName().equals(name)){
                this.data.remove(pet);
                return true;
            }
        }
        return false;
    }

    public Pet getPet(String name, String owner){
        return  this.data.stream()
                .filter(p->p.getName().equals(name) && p.getOwner().equals(owner))
                .findFirst()
                .orElse(null);
    }

//    public Pet getOldestPet(){
//        return this.data.stream().max(Comparator.comparing(Pet::getAge)).get();
//    }

    public int getCount(){
        return this.data.size();
    }

    public String getStatistics(){
        StringBuilder builder = new StringBuilder();
        builder.append("The grooming salon has the following clients:").append(System.lineSeparator());
        for (Pet pet : this.data) {
            builder.append(String.format("%s %s", pet.getName(), pet.getOwner())).append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
