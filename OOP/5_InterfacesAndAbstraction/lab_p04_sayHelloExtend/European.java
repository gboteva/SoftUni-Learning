package lab_p04_sayHelloExtend;

public class European extends BasePerson {

    public European(String name) {
       super(name);
    }


    @Override
    public String sayHello() {
        return "Hello";
    }
}
