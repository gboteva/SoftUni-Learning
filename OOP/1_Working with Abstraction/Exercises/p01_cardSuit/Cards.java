package Exercises.p01_cardSuit;

public enum Cards {
    CLUBS (0),
    DIAMONDS(1),
    HEARTS(2),
    SPADES(3);

    int ordinalValue;

    Cards(int ordinalValue) {
        this.ordinalValue = ordinalValue;
    }

    public int getOrdinalValue() {
        return ordinalValue;
    }
}
