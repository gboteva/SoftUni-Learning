package Exercises.p03_CardsWithPower;

public class Card {
    private CardDeck type;
    private CardSuit color;
    private int power;

    public Card(CardDeck type, CardSuit color) {
        this.type = type;
        this.color = color;
        this.power = type.getPower() + color.getOrdinalValue();
    }

    public int getPower() {
        return power;
    }
}
