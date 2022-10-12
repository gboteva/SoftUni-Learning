package ex_p02_animalFarm;

public class Chicken {
    private String name;
    private int age;

    public Chicken (String name, int age){
        this.setName(name);
        this.setAge(age);
    }

    private void setName (String name){
        if (!name.trim().isEmpty()){
            this.name = name;
        }else {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
    }

    private void setAge (int age){
        if (age>=0 && age<=15){
            this.age = age;
        }else {
            throw new IllegalArgumentException("Age should be between 0 and 15.");
        }
    }

    public double productPerDay (){
        return this.calculateProductPerDay();
    }

    public String toString(){
        return String.format("Chicken %s (age %d) can produce %.2f eggs per day.", this.name, this.age, this.productPerDay());
    }

    private double calculateProductPerDay(){
        double countEggs = 0;
        if (age<6){
            countEggs=2;
        } else if (age<12){
            countEggs=1;
        }else if (age<=15){
            countEggs=0.75;
        }
        return countEggs;
    }

}
