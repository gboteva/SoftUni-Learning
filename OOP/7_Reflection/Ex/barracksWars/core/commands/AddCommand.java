package barracksWars.core.commands;

import barracksWars.interfaces.Repository;
import barracksWars.interfaces.Unit;
import barracksWars.interfaces.UnitFactory;
import jdk.jshell.spi.ExecutionControl;

public class AddCommand extends Command{
    public AddCommand(String[] data, Repository repository, UnitFactory unitFactory) {
        super(data, repository, unitFactory);
    }

    @Override
    public String execute() {
        String unitType = getData()[1];
        try{
            Unit unitToAdd = getUnitFactory().createUnit(unitType);
            getRepository().addUnit(unitToAdd);
            String output = unitType + " added!";
            return output;
        } catch (ExecutionControl.NotImplementedException e) {
            e.printStackTrace();
        }
       return null;
    }
}
