package iteratorsAndComparators.comparingObjects;

public class Person implements Comparable<Person> {
    private String name;
    private int age;
    private String town;

    public Person(String name, int age, String town) {
        this.name = name;
        this.age = age;
        this.town = town;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getTown() {
        return town;
    }

    @Override
    public int compareTo(Person secondPerson) {
        int result;
        result = this.name.compareTo(secondPerson.getName());
        if (result == 0){
            result = Integer.compare(this.age, secondPerson.getAge());
        }
        if (result == 0){
            result = this.town.compareTo(secondPerson.getTown());
        }
        return result;
    }
}
