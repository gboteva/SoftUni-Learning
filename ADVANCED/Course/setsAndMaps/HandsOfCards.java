package setsAndMaps;

import java.util.*;
import java.util.stream.Collectors;

public class HandsOfCards {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        Map<String, Set<String>> players = new LinkedHashMap<>();

        while (!input.equals("JOKER")) {
            String[] tokens = input.split(": ");
            String name = tokens[0];
            List<String> deck = Arrays.stream(tokens[1].split(", ")).collect(Collectors.toList());

            players.putIfAbsent(name, new HashSet<>());
            deck.forEach(card -> players.get(name).add(card));

            input = scanner.nextLine();
        }

        for (Map.Entry<String, Set<String>> entry : players.entrySet()) {
            String name = entry.getKey();
            Set<String> deck = entry.getValue();
            int score = getScoreFromDeck(deck);

            System.out.println(name + ": " + score);

        }

    }

    private static int getScoreFromDeck(Set<String> deck) {
        int score = 0;
        Iterator<String> iterator = deck.iterator();

        while (iterator.hasNext()) {
            String card = iterator.next();
            int power = 1;
            int type = 1;
            if (card.length() == 2) {
                char cardPower = card.charAt(0);
                power = getPower(cardPower);
                char cardType = card.charAt(1);
                type = getType(cardType);
            } else {
                power = 10;
                char cardType = card.charAt(2);
                type = getType(cardType);
            }
            int result = power * type;
            score += result;
        }

        return score;
    }

    private static int getType(char cardType) {
        //S -> 4, H-> 3, D -> 2, C -> 1
        switch (cardType) {
            case 'S':
                return 4;
            case 'H':
                return 3;
            case 'D':
                return 2;
            case 'C':
                return 1;
        }
        return 0;
    }

    public static int getPower(char power) {
        switch (power) {
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;
            case 'J':
                return 11;
            case 'Q':
                return 12;
            case 'K':
                return 13;
            case 'A':
                return 14;
        }
        return 0;
    }
}
