package easterRaces.entities.cars;

import static easterRaces.common.ExceptionMessages.INVALID_HORSE_POWER;

public class MuscleCar extends BaseCar{

    private final static double CUBIC_CM_VALUE = 5000;

    public MuscleCar(String model, int horsePower) {
        super(model, horsePower, CUBIC_CM_VALUE);
        setHorsePowerInRange(horsePower);
    }


    private void setHorsePowerInRange(int horsePower){
        if (horsePower < 400 || horsePower > 600){
            throw new IllegalArgumentException(String.format(INVALID_HORSE_POWER, horsePower));
        }

        setHorsePower(horsePower);
    }
}
