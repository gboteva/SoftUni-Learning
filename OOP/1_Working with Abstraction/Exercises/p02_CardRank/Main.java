package Exercises.p02_CardRank;

public class Main {
    public static void main(String[] args) {
        System.out.println("Card Ranks:");
        Cards[] cards = Cards.values();
        for (Cards card : cards ){
            System.out.printf("Ordinal value: %d; Name value: %s%n", Cards.counter, card.name());
            Cards.counter++;
        }

    }
}
