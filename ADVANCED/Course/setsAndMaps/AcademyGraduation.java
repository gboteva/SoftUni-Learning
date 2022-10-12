package setsAndMaps;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class AcademyGraduation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        Map<String, List<Double>> studentsScore = new TreeMap<>();

        while (n-- > 0){
            String studentName= scanner.nextLine();
            List<Double> scores = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .map(Double::parseDouble).collect(Collectors.toList());

            studentsScore.putIfAbsent(studentName, scores);
        }

        for (Map.Entry<String, List<Double>> entry : studentsScore.entrySet()) {
            String name = entry.getKey();
            double sum = 0;
            List<Double>scores = entry.getValue();
            for (Double score : scores) {
                sum+=score;
            }

            DecimalFormat df = new DecimalFormat("#.#####################");
            System.out.printf("%s is graduated with %s%n", name, df.format(sum/scores.size()));
        }

    }
}
