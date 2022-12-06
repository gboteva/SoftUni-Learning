package fairyShop.models;

public class Sleepy extends BaseHelper{
    private final static int INITIAL_ENERGY = 50;

    public Sleepy(String name) {
        super(name, INITIAL_ENERGY);
    }

    @Override
    public void work() {
     setEnergy(Math.max(0, this.getEnergy() - 10));
    }
}
