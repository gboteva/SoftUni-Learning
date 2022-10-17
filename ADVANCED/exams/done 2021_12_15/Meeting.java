package exams.december2021;

import java.util.*;
import java.util.stream.Collectors;

public class Meeting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayDeque<Integer> males = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer::parseInt)
                .forEach(males::push);

        ArrayDeque<Integer> females = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        int matches = 0;

        while (!males.isEmpty() && !females.isEmpty()) {
            int female = females.peek();
            int male = males.peek();

            if (female <= 0) {
                females.poll();
                continue;
            } else if (male <= 0) {
                males.pop();
                continue;
            }

            if (female % 25 == 0) {
                females.poll();
                females.poll();
                continue;
            } else if (male % 25 == 0) {
                males.pop();
                males.pop();
                continue;
            }


            if (female == male) {
                matches++;
                females.poll();
                males.pop();
            } else {
                females.poll();
                male -= 2;
                males.pop();
                males.push(male);
            }

        }

        System.out.println("Matches: " + matches);

        if (males.isEmpty()) {
            System.out.println("Males left: none");
        } else {
            List<String> list = new ArrayList<>();
            males.forEach(m -> list.add(String.valueOf(m)));
            System.out.println("Males left: " + String.join(", ", list));
        }
        if (females.isEmpty()) {
            System.out.println("Females left: none");
        } else {
            List<String> list = new ArrayList<>();
            females.forEach(f -> list.add(String.valueOf(f)));
            System.out.println("Females left: " + String.join(", ", list));
        }
    }
}

