package DefiningClasses.ex.P02_CompanyRoster;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        Map<String, List<Employee>> map = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String[] token = scanner.nextLine().split("\\s+");
            String name = token[0];
            double salary = Double.parseDouble(token[1]);
            String position = token[2];
            String department = token[3];
            Employee employee;
            if (token.length == 6) {
                String email = token[4];
                int age = Integer.parseInt(token[5]);
                employee = new Employee(name, salary, position, department, email, age);
            } else if (token.length == 4) {
                employee = new Employee(name, salary, position, department);
            } else {
                employee = new Employee(name, salary, position, department);
                if (token[4].contains("@")) {
                    employee.setEmail(token[4]);
                } else {
                    employee.setAge(Integer.parseInt(token[4]));
                }
            }

            if (map.get(department)==null){
                map.put(department, new ArrayList<>());
                map.get(department).add(employee);
            }else {
                map.get(department).add(employee);
            }

        }

      String maxAverageSalaryDeparment =  map.entrySet().stream()
                .max(Comparator.comparingDouble(entry->getAverageSalary(entry.getValue())))
                .get()// взима ентрито
                .getKey();

        System.out.println("Highest Average Salary: " + maxAverageSalaryDeparment);

        map.get(maxAverageSalaryDeparment).sort(Comparator.comparingDouble(employee-> employee.getSalary()));
        Collections.reverse(map.get(maxAverageSalaryDeparment));
        List<Employee> list = map.get(maxAverageSalaryDeparment);
        for ( Employee employee: list ){
            System.out.println(employee.toString());
        }

    }


    public static double getAverageSalary(List<Employee> employees){
        double sum = 0;
        for ( Employee employee :employees ){
            sum+=employee.getSalary();
        }
        return sum/employees.size();
    }
}
