package beforeCourse.AssociativeArrays_7.ex;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class P10_SoftUniResults {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        Map<String, Integer> usersPoint = new LinkedHashMap<>();
        Map<String, Integer> countSubmissions = new LinkedHashMap<>();

        while (!input.equals("exam finished")) {
            if (!input.contains("banned")) {
                String user = input.split("-")[0];
                String lenguage = input.split("-")[1];
                int points = Integer.parseInt(input.split("-")[2]);
                if (usersPoint.get(user) == null) {
                    usersPoint.put(user, points);
                } else if (usersPoint.get(user) < points) {
                    usersPoint.put(user, points);
                }

                if (countSubmissions.get(lenguage) == null) {
                    countSubmissions.put(lenguage, 1);
                } else {
                    int currentSubmit = countSubmissions.get(lenguage);
                    countSubmissions.put(lenguage, currentSubmit + 1);
                }

            } else {
                String user = input.split("-")[0];
                for (Map.Entry<String, Integer> entry : usersPoint.entrySet()) {
                    if (entry.getKey().equals(user)) {
                        usersPoint.remove(entry.getKey());
                    }
                }
            }

            input = scanner.nextLine();
        }
        System.out.println("Results:");
        for (Map.Entry<String, Integer> entry : usersPoint.entrySet()) {
            System.out.println(entry.getKey() + " | " + entry.getValue());
        }

        System.out.println("Submissions:");
        for (Map.Entry<String, Integer> entry : countSubmissions.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }


    }

}
