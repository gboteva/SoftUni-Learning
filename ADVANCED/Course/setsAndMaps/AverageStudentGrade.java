package setsAndMaps;

import java.util.*;

public class AverageStudentGrade {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        Map<String, List<Double>> studentsGrade = new TreeMap<>();

        while (n-- > 0) {
            String[] tokens = scanner.nextLine().split("\\s+");
            String name = tokens[0];
            double grade =Double.parseDouble(tokens[1]);

            studentsGrade.putIfAbsent(name, new ArrayList<>());

            studentsGrade.get(name).add(grade);

        }

        for (Map.Entry<String, List<Double>> entry : studentsGrade.entrySet()) {
            System.out.print(entry.getKey() + " -> ");

            for (int i = 0; i < entry.getValue().size(); i++) {
                System.out.printf("%.2f ",entry.getValue().get(i));
            }

            double sum = 0;
            for (Double s : entry.getValue()) {
                sum+= s;
            }
            double average = sum / entry.getValue().size();
            System.out.printf("(avg: %.2f)%n",average);
        }
    }
}
