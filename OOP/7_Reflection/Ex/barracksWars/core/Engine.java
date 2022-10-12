package barracksWars.core;

import barracksWars.interfaces.Repository;
import barracksWars.interfaces.Runnable;
import barracksWars.interfaces.Unit;
import barracksWars.interfaces.UnitFactory;
import jdk.jshell.spi.ExecutionControl;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;

public class Engine implements Runnable {

	private Repository repository;
	private UnitFactory unitFactory;

	public Engine(Repository repository, UnitFactory unitFactory) {
		this.repository = repository;
		this.unitFactory = unitFactory;
	}

	@Override
	public void run() {
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(System.in));
		while (true) {
			try {
				String input = reader.readLine();
				String[] data = input.split("\\s+");
				String commandName = data[0];
				String result = interpretCommand(data, commandName);
				if (result.equals("fight")) {
					break;
				}
				System.out.println(result);
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// TODO: refactor for problem 4
	//add "retire"
	private String interpretCommand(String[] data, String commandName)  {
		String result;
		switch (commandName) {
			case "add":
				result = this.addUnitCommand(data);
				break;
			case "report":
				result = this.reportCommand(data);
				break;
			case "fight":
				result = this.fightCommand(data);
				break;
			case "retire":
				result = this.removeUnit(data);
				break;
			default:
				throw new RuntimeException("Invalid command!");
		}
		return result;
	}

	private String reportCommand(String[] data) {
		String output = this.repository.getStatistics();
		return output;
	}

	private String addUnitCommand(String[] data)  {
		String unitType = data[1];
		Unit unitToAdd = null;

		unitToAdd = this.unitFactory.createUnit(unitType);
		this.repository.addUnit(unitToAdd);
		String output = unitType + " added!";
		return output;
	}

	private String removeUnit(String[] data){
		String unitType = data[1];

		String output = "";

		try {
			this.repository.removeUnit(unitType);
			output = unitType + " retired!";
		} catch (Exception e){
			output = "No such units in repository.";
		}

		return output;
	}

	private String fightCommand(String[] data) {
		return "fight";
	}
}
