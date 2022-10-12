package setsAndMaps;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Voina {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Set<Integer> firstDeck = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt).collect(Collectors.toCollection(LinkedHashSet::new));

        Set<Integer> secondDeck = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt).collect(Collectors.toCollection(LinkedHashSet::new));

        int round = 50;

        while (round-- > 0) {
            int firstPlayerCard = firstDeck.iterator().next();
            int secondPlayerCard = secondDeck.iterator().next();
            firstDeck.remove(firstPlayerCard);
            secondDeck.remove(secondPlayerCard);

            if (firstPlayerCard > secondPlayerCard) {
                firstDeck.add(firstPlayerCard);
                firstDeck.add(secondPlayerCard);
            } else if (secondPlayerCard > firstPlayerCard) {
                secondDeck.add(firstPlayerCard);
                secondDeck.add(secondPlayerCard);
            }

            if (firstDeck.isEmpty()|| secondDeck.isEmpty()){
                break;
            }
        }

        if (firstDeck.size() > secondDeck.size()){
            System.out.println("First player win!");
        }else if (secondDeck.size() >  firstDeck.size()){
            System.out.println("Second player win!");
        }else {
            System.out.println("Draw!");
        }

    }
}
