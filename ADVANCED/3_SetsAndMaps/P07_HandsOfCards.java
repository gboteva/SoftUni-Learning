package SetsAndMaps_03.ex;

import java.util.*;
import java.util.stream.Collectors;

public class P07_HandsOfCards {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Map<String, Set<String>> players = new LinkedHashMap<>();

        while (!input.equals("JOKER")){
            String [] arr = input.split(": ");
            String name = arr[0];
            List<String> cardsList = Arrays.stream(arr[1].split(", ")).collect(Collectors.toList());
            Set<String> cards = new LinkedHashSet<>(cardsList);
            if (!players.containsKey(name)){
                players.put(name, cards);
            }else {
              Set<String> addedCards = players.get(name);
              addedCards.addAll(cards);
              players.put(name,addedCards);
            }
            input = scanner.nextLine();
        }

        Map<String,Integer> cardsValues = new HashMap<>();
        cardsValues = getCardsValue();

        for (Map.Entry<String, Set<String>> entry : players.entrySet()) {
            String name = entry.getKey();
            List<String> cards = entry.getValue().stream().collect(Collectors.toList()); //сета с карти на всеки играч
            int totalPoint = 0;
            for ( String card:cards ){
                String type = String.valueOf(card.charAt(card.length()-1));
                int currentPoint = 0;
                if (card.startsWith("10")){
                    currentPoint+=10 * cardsValues.get(type);
                }else {
                    String power = String.valueOf(card.charAt(0));
                    currentPoint+=cardsValues.get(power) * cardsValues.get(type);
                }
                totalPoint+=currentPoint;
            }
            System.out.printf("%s: %d%n", name, totalPoint);

        }

    }

    private static Map<String, Integer> getCardsValue() {
        Map<String, Integer> cards = new HashMap<>();
        cards.put("2", 2);
        cards.put("3", 3);
        cards.put("4", 4);
        cards.put("5", 5);
        cards.put("6", 6);
        cards.put("7", 7);
        cards.put("8", 8);
        cards.put("9", 9);
        cards.put("J", 11);
        cards.put("Q", 12);
        cards.put("K", 13);
        cards.put("A", 14);
        cards.put("S", 4);
        cards.put("H", 3);
        cards.put("D", 2);
        cards.put("C", 1);
        return cards;
    }
}
