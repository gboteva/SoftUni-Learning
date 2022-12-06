package football.entities.player;

public class Women extends BasePlayer{

    private final static double KG_VALUE = 60;

    public Women(String name, String nationality,  int strength) {
        super(name, nationality, KG_VALUE, strength);
    }

    @Override
    public void stimulation() {
        super.setStrength(super.getStrength() + 115);
    }
}
