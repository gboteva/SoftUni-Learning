package hotel;

import java.util.ArrayList;
import java.util.Collection;

public class Hotel {
    private String name;
    private int capacity;
    private Collection<Person> roster;

    public Hotel(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.roster = new ArrayList<>();
    }

    public void add(Person person) {
        if (this.roster.size() < capacity) {
            this.roster.add(person);
        }
    }

    public boolean remove(String name) {
        for (Person person : this.roster) {
            if (person.getName().equals(name)) {
                this.roster.remove(person);
                return true;
            }
        }
        return false;
    }

    public Person getPerson(String name, String hometown) {
        return this.roster.stream()
                .filter(p -> p.getName().equals(name) && p.getHometown().equals(hometown))
                .findFirst()
                .orElse(null);
    }

    public int getCount (){
        return this.roster.size();
    }

    public String getStatistics(){
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("The people in the hotel %s are:", this.name)).append(System.lineSeparator());
        for (Person person : this.roster) {
            builder.append(person).append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
