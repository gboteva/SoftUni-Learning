package vetClinic;

import java.util.ArrayList;
import java.util.List;

public class Clinic {
    private int capacity;
    private List<Pet> data;

    public Clinic(int capacity) {
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public void add (Pet pet){
        if (capacity>this.data.size()){
            this.data.add(pet);
        }
    }

    public boolean remove (String name){
        int indexToRemove = -1;
        for (Pet pet : this.data){
            if (pet.getName().equals(name)){
                indexToRemove = this.data.indexOf(pet);
                break;
            }
        }
        if (indexToRemove>-1){
            this.data.remove(indexToRemove);
            return true;
        }else {
            return false;
        }
    }

    public Pet getPet (String name, String owner){
        for (Pet pet : this.data){
            if (pet.getName().equals(name) && pet.getOwner().equals(owner)){
                return pet;
            }
        }
        return null;
    }

    public Pet getOldestPet(){
        int maxAge= 0;
        Pet oldestPet = null;
        for (Pet pet : this.data ){
            if (pet.getAge()>maxAge){
                maxAge= pet.getAge();
                oldestPet = pet;
            }
        }
        if (oldestPet==null){
            return null;
        }else {
            return oldestPet;
        }
    }

    public int getCount (){
        return this.data.size();
    }

    public String getStatistics() {
        StringBuilder builder = new StringBuilder();
        builder.append("The clinic has the following patients:");
        for (Pet pet : data ){
            builder.append("\n").append(pet.getName()).append(" ").append(pet.getOwner());
        }
        return builder.toString();
    }
}
