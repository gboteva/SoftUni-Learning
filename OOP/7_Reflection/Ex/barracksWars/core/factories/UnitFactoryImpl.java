package barracksWars.core.factories;

import barracksWars.interfaces.Unit;
import barracksWars.interfaces.UnitFactory;
import jdk.jshell.spi.ExecutionControl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class UnitFactoryImpl implements UnitFactory {

	private static final String UNITS_PACKAGE_NAME =
			"barracksWars.models.units.";

	@Override
	public Unit createUnit(String unitType)  {
		// TODO: implement for problem 3 - done

		try {
			//1. взимаме класа, който ни трябва
			Class unitReflection = Class.forName(UNITS_PACKAGE_NAME + unitType);

			//2. взимаме му конструктора и го правим достъпен:
			Constructor <Unit> constructor = unitReflection.getDeclaredConstructor();
			constructor.setAccessible(true);

			//създаваме unit
			return constructor.newInstance();
		} catch (Exception e){

		}
		return null;
	}
}
