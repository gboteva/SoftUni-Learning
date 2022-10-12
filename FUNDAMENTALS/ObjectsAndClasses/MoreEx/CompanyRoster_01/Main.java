package FundamentalsCourse.objectsAndClasses_6.MoreEx.CompanyRoster_01;

import FundamentalsCourse.objectsAndClasses_6.MoreEx.CompanyRoster_01.Employee;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        Map<String, List<Employee>> departments = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String[] arr = scanner.nextLine().split("\\s+");
            String name = arr[0];
            double salary = Double.parseDouble(arr[1]);
            String position = arr[2];
            String department = arr[3];
            Employee employee = new Employee(name, salary, position, department);

            if (arr.length == 6) {
                String email = arr[4];
                int age = Integer.parseInt(arr[5]);
                employee.setEmail(email);
                employee.setAge(age);
            } else if (arr.length == 5) {
                if (arr[4].contains("@")) {
                    String email = arr[4];
                    employee.setEmail(email);
                } else {
                    int age = Integer.parseInt(arr[4]);
                    employee.setAge(age);
                }
            }


            departments.putIfAbsent(department, new ArrayList<>());
            departments.get(department).add(employee);


        }

        departments.entrySet().stream().sorted((a, b) -> {
            double avgFirst = a.getValue().stream().mapToDouble(Employee::getSalary).average().orElse(Double.NaN);
            double avgSecond = b.getValue().stream().mapToDouble(Employee::getSalary).average().orElse(Double.NaN);
            return Double.compare(avgSecond, avgFirst);
        }).limit(1).forEach(e -> {
            System.out.println(String.format("Highest Average Salary: " + e.getKey()));
            e.getValue().stream().sorted((a, b) -> Double.compare(b.getSalary(), a.getSalary()))
                    .forEach(o -> System.out.println(String.format("%s %.2f %s %d",
                            o.getName(), o.getSalary(), o.getEmail(), o.getAge())));
        });


    }

}


