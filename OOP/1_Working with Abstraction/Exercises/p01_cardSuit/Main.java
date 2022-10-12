package Exercises.p01_cardSuit;

public class Main {
    public static void main(String[] args) {
        System.out.println("Card Suits:");
        Cards [] cardSuit = Cards.values();
        for (Cards card : cardSuit ){
            System.out.printf("Ordinal value: %d; Name value: %s%n", card.getOrdinalValue(), card.name());
        }
    }
}
