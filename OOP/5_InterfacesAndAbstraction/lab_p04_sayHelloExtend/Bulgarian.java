package lab_p04_sayHelloExtend;

public class Bulgarian extends BasePerson {

    public Bulgarian(String name) {
        super(name);
    }


    @Override
    public String sayHello() {
        return "Здравей";
    }
}
