package Exercises.p03_CardsWithPower;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String cardRank = scanner.nextLine();
        String cardColor = scanner.nextLine();

        Card card = new Card(CardDeck.valueOf(cardRank), CardSuit.valueOf(cardColor));
        System.out.printf("Card name: %s of %s; Card power: %d", cardRank,cardColor, card.getPower() );
    }
}
