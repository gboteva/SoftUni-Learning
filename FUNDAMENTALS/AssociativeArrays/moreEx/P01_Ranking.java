package FundamentalsCourse.AssociativeArrays_7.moreEx;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class P01_Ranking {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Map<String, String> contests = new LinkedHashMap<>();
        while (!input.equals("end of contests")) {
            String contest = input.split(":")[0];
            String password = input.split(":")[1];
            contests.put(contest, password);

            input = scanner.nextLine();
        }

        String secondInput = scanner.nextLine();
        //          Name,               contest, points
        TreeMap<String, LinkedHashMap<String, Integer>> users = new TreeMap<>();

        while (!secondInput.equals("end of submissions")) {
            String contest = secondInput.split("=>")[0];
            String password = secondInput.split("=>")[1];
            String userName = secondInput.split("=>")[2];
            int userPoints = Integer.parseInt(secondInput.split("=>")[3]);

            if (contests.containsKey(contest)) {
                if (contests.get(contest).equals(password)) {
                    LinkedHashMap<String, Integer> course = new LinkedHashMap<>();
                    course.put(contest, userPoints);

                    if (!users.containsKey(userName)) {
                        users.put(userName, course);
                    } else {
                        if (!users.get(userName).containsKey(contest)) {
                            users.get(userName).put(contest, userPoints);
                        } else {
                            users.get(userName).put(contest, Math.max(users.get(userName).get(contest), userPoints));
                        }
                    }
                }
            }
            secondInput = scanner.nextLine();
        }

        int maxPoints = 0;
        for (Map.Entry<String, LinkedHashMap<String, Integer>> entry : users.entrySet()) {
            int sum = entry.getValue().values().stream().mapToInt(i -> i).sum();
            if (sum > maxPoints) {
                maxPoints = sum;
            }
        }

        for (Map.Entry<String, LinkedHashMap<String, Integer>> entry : users.entrySet()) {
            if (entry.getValue().values().stream().mapToInt(i -> i).sum() == maxPoints) {
                System.out.printf("Best candidate is %s with total %d points.%n", entry.getKey(), maxPoints);
            }
        }
        System.out.println("Ranking: ");

        users.forEach((key,value)-> {
            System.out.printf("%s%n", key);
            value.entrySet().stream().sorted((p1,p2)->p2.getValue() - p1.getValue()).
                    forEach(i-> System.out.printf("#  %s -> %d%n", i.getKey(), i.getValue()));
        });


    }
}
