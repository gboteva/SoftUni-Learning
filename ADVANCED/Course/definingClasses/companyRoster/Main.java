package definingClasses.companyRoster;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        //departments and employees
        Map<String, List<Employee>> employeeMap = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");
            //name, salary, position, department, email, and age
            String name = tokens[0];
            double salary = Double.parseDouble(tokens[1]);
            String position = tokens[2];
            String department = tokens[3];

            Employee employee = new Employee(name, salary, position, department);
            if (tokens.length == 5) {
                if (tokens[4].contains("@")) {
                    employee.setEmail(tokens[4]);
                } else {
                    employee.setAge(Integer.parseInt(tokens[4]));
                }
            } else if (tokens.length == 6) {
                employee.setEmail(tokens[4]);
                employee.setAge(Integer.parseInt(tokens[5]));
            }

            employeeMap.putIfAbsent(department, new ArrayList<>());
            employeeMap.get(department).add(employee);
        }

        //departments and employees
        Map<String, Double> averageMap = new LinkedHashMap<>();
        for (Map.Entry<String, List<Employee>> dep : employeeMap.entrySet()) {
            String department = dep.getKey();
            List<Employee> employees = dep.getValue();
            double averageSalary = employees.stream().mapToDouble(Employee::getSalary).average().orElse(0);
            averageMap.put(department, averageSalary);
        }

        averageMap.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(1).forEach(e-> System.out.println("Highest Average Salary: " + e.getKey()));

        averageMap.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(1)
                .forEach(d-> employeeMap.get(d.getKey())
                        .stream().sorted((first, second)-> Double.compare(second.getSalary(), first.getSalary()))
                        .forEach(employee
                                -> System.out.printf("%s %.2f %s %d%n", employee.getName(), employee.getSalary()
                        , employee.getEmail(), employee.getAge())));
    }
}
