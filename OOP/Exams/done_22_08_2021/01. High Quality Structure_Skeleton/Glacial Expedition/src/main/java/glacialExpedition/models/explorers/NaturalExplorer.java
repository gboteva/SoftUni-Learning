package glacialExpedition.models.explorers;

public class NaturalExplorer extends BaseExplorer{
    private final static double INITIAL_ENERGY = 60;

    public NaturalExplorer(String name) {
        super(name, INITIAL_ENERGY);
    }

    @Override
    public void search() {
        double decreaseEnergy = super.getEnergy() - 7;
        super.setEnergy(Math.max(0, decreaseEnergy));
    }
}
