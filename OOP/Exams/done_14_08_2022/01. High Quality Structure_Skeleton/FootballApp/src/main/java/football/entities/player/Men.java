package football.entities.player;

public class Men extends BasePlayer{

    private final static double KG_VALUE = 85.5;

    public Men(String name, String nationality,int strength) {
        super(name, nationality, KG_VALUE, strength);
    }

    @Override
    public void stimulation() {
        super.setStrength(super.getStrength() + 145);
    }
}
