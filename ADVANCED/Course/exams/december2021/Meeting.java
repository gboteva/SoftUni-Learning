package exams.december2021;

import java.util.*;

public class Meeting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayDeque<Integer> males = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer::parseInt)
                .forEach(males::push);

        ArrayDeque<Integer> females = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer::parseInt)
                .forEach(females::offer);

        int matches = 0;

        while (!males.isEmpty() && !females.isEmpty()) {
            int female = females.peek();
            int male = males.peek();

            if (isSomeoneZero(female, male)){
                if (female==0){
                    females.poll();
                }else {
                    males.pop();
                }

            }else if (divisibleBy25(female, male)) {
                if (female % 25 == 0) {
                    females.poll();
                    females.poll();
                }
                if (male % 25 == 0) {
                    males.pop();
                    males.pop();
                }
            }else if (female == male) {
                females.poll();
                males.pop();
                matches++;
            }else {
                females.poll();
                males.pop();
                male-=2;
                males.push(male);
            }
        }
        System.out.println("Matches: " + matches);

        if (males.isEmpty()){
            System.out.println("Males left: none");
        }else {
            List<String> list = new ArrayList<>();
            males.forEach(m-> list.add(String.valueOf(m)));
            System.out.println("Males left: " + String.join(", ", list));
        }

        if (females.isEmpty()){
            System.out.println("Females left: none");
        }else {
            List<String> list = new ArrayList<>();
            females.forEach(f-> list.add(String.valueOf(f)));
            System.out.println("Females left: " + String.join(", ", list));
        }

    }

    private static boolean isSomeoneZero(int female, int male) {
        return female <= 0 || male <= 0;
    }

    private static boolean divisibleBy25(int female, int male) {
        return male % 25 == 0 || female % 25 == 0;
    }

}

