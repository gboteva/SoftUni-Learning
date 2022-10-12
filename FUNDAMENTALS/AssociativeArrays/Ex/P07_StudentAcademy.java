package beforeCourse.AssociativeArrays_7.ex;

import java.util.*;

public class P07_StudentAcademy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Map<String, List<Double>> studentsMap = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String name = scanner.nextLine();
            double grade = Double.parseDouble(scanner.nextLine());

            if (!studentsMap.containsKey(name)) {
                studentsMap.put(name, new ArrayList<>());
            }
            studentsMap.get(name).add(grade);

        }

        Map<String, Double> averageGradeMap = new LinkedHashMap<>();

        for (Map.Entry<String, List<Double>> entry: studentsMap.entrySet() ){
            List<Double> grade = entry.getValue();
            double average = grade.stream().mapToDouble(x->x).average().getAsDouble();
            String studentName = entry.getKey();
            if (average>=4.5){
                averageGradeMap.put(studentName,average);
            }
        }

        for (Map.Entry<String, Double> entry : averageGradeMap.entrySet() ){
            System.out.printf("%s -> %.2f%n", entry.getKey(), entry.getValue());
        }
    }
}
