package lab_p03_animals;

public class Cat extends Animal{

    public Cat(String name, String favouriteFood) {
        super(name, favouriteFood);
    }

    @Override
    public String explainSelf(){
        return super.toString().concat("MEEOW");
    }
}
