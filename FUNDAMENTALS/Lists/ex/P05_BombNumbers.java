package beforeCourse.lists_5.ex;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P05_BombNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> input = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> bombNumbers = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());

        int bombNum = bombNumbers.get(0);
        int bombPower = bombNumbers.get(1);
        int bombIndex = -1;

        while (input.contains(bombNum)) {
            for (int i = 0; i < input.size(); i++) {
                if (input.get(i).equals(bombNum)) {
                    bombIndex = i;
                    break;
                }
            }

            int fromIndex = bombIndex - bombPower;
            int toIndex = bombIndex + bombPower;
            if (fromIndex < 0) {
                fromIndex = 0;
            }
            if (toIndex > input.size() - 1) {
                toIndex = input.size() - 1;
            }

            for (int i = fromIndex; i <= toIndex; i++) {
                input.remove(i);
                toIndex -= 1;
                i = fromIndex - 1;
            }
        }
        int sum = 0;
        for (int i = 0; i < input.size(); i++) {
            sum += input.get(i);
        }
        System.out.println(sum);
    }
}
