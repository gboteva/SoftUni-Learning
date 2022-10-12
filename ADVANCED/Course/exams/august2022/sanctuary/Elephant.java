package exams.august2022.sanctuary;

public class Elephant {
    private String name;
    private int age;
    private String retiredFrom;


    public Elephant(String name, int age, String retiredFrom) {
        this.name = name;
        this.age = age;
        this.retiredFrom = retiredFrom;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setRetiredFrom(String retiredFrom) {
        this.retiredFrom = retiredFrom;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public String getRetiredFrom() {
        return this.retiredFrom;
    }


    @Override
    public String toString() {
        return String.format("%s %d - %s", getName(), getAge(), getRetiredFrom());
    }
}
