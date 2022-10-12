package Exercises.p03_CardsWithPower;

public enum CardSuit {
    CLUBS (0),
    DIAMONDS(13),
    HEARTS(26),
    SPADES(39);

    int ordinalValue;

    CardSuit(int ordinalValue) {
        this.ordinalValue = ordinalValue;
    }

    public int getOrdinalValue() {
        return ordinalValue;
    }
}
