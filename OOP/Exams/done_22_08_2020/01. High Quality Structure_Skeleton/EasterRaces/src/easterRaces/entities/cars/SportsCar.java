package easterRaces.entities.cars;

import static easterRaces.common.ExceptionMessages.INVALID_HORSE_POWER;

public class SportsCar extends BaseCar{
    private final static double CUBIC_CM_VALUE = 3000;

    public SportsCar(String model, int horsePower) {
        super(model, horsePower, CUBIC_CM_VALUE);
        setHorsePowerInRange(horsePower);
    }

    private void setHorsePowerInRange(int horsePower){
        if (horsePower < 250 || horsePower > 450){
            throw new IllegalArgumentException(String.format(INVALID_HORSE_POWER, horsePower));
        }

        setHorsePower(horsePower);
    }
}
