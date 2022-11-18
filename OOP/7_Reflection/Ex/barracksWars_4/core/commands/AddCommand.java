package barracksWars.core.commands;

import barracksWars.annotations.Inject;
import barracksWars.interfaces.Repository;
import barracksWars.interfaces.Unit;
import barracksWars.interfaces.UnitFactory;
import jdk.jshell.spi.ExecutionControl;

public class AddCommand extends Command{
    @Inject
    private Repository repository;
    @Inject
    private UnitFactory unitFactory;

    public AddCommand(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
        String unitType = getData()[1];
        try{
            Unit unitToAdd = unitFactory.createUnit(unitType);
            repository.addUnit(unitToAdd);
            String output = unitType + " added!";
            return output;
        } catch (ExecutionControl.NotImplementedException e) {
            e.printStackTrace();
        }
       return null;
    }
}
