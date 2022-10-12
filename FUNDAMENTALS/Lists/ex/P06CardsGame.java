package beforeCourse.lists_5.ex;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P06CardsGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> firstPlayerCards = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> secondPlayerCards = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        int size = firstPlayerCards.size();

                for (int i = 0; i < size; i++) {
                if (firstPlayerCards.get(i) > secondPlayerCards.get(i)) {
                    firstPlayerCards.add(firstPlayerCards.get(i));
                    firstPlayerCards.add(secondPlayerCards.get(i));
                    secondPlayerCards.remove(i);
                    firstPlayerCards.remove(i);
                    i=-1;
                } else if (secondPlayerCards.get(i) > firstPlayerCards.get(i)) {
                    secondPlayerCards.add(secondPlayerCards.get(i));
                    secondPlayerCards.add(firstPlayerCards.get(i));
                    secondPlayerCards.remove(i);
                    firstPlayerCards.remove(i);
                    i=-1;
                } else {
                    secondPlayerCards.remove(i);
                    firstPlayerCards.remove(i);
                    i=-1;
                }
                if (firstPlayerCards.isEmpty()|| secondPlayerCards.isEmpty()){
                    break;
                }
            }


        if (firstPlayerCards.isEmpty()) {
            int sum = 0;
            for (int i = 0; i < secondPlayerCards.size(); i++) {
                sum += secondPlayerCards.get(i);
            }
            System.out.printf("Second player wins! Sum: %d", sum);
        } else if (secondPlayerCards.isEmpty()) {
            int sum = 0;
            for (int i = 0; i < firstPlayerCards.size(); i++) {
                sum += firstPlayerCards.get(i);
            }
            System.out.printf("First player wins! Sum: %d", sum);
        }
    }
}
