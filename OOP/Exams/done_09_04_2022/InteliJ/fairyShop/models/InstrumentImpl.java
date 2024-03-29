package fairyShop.models;

import static fairyShop.common.ExceptionMessages.INSTRUMENT_POWER_LESS_THAN_ZERO;

public class InstrumentImpl implements Instrument{

    private int power;

    public InstrumentImpl(int power) {
       setPower(power);
    }

    private void setPower(int power){
        if (power < 0){
            throw new IllegalArgumentException(INSTRUMENT_POWER_LESS_THAN_ZERO);
        }

        this.power = power;
    }

    @Override
    public int getPower() {
        return this.power;
    }

    @Override
    public void use() {
        this.power = Math.max(0, this.power - 10);
    }

    @Override
    public boolean isBroken() {
        if (this.power == 0){
            return true;
        }
        return false;
    }
}
