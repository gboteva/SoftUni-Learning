package beforeCourse.AssociativeArrays_7.ex;

import java.util.*;

public class P08CompanyUsers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        Map<String, List<String>> companyMap = new LinkedHashMap<>();

        while (!command.equals("End")){
            String companyName = command.split(" -> ")[0];
            String employeeID = command.split(" -> ")[1];
            if (!companyMap.containsKey(companyName)){
                companyMap.put(companyName,new ArrayList<>());
                companyMap.get(companyName).add(employeeID);
            }else {
                    List<String> employeesList =  companyMap.get(companyName);
                    if (!employeesList.contains(employeeID)){
                        companyMap.get(companyName).add(employeeID);
                    }
                }
            command= scanner.nextLine();
            }

        companyMap.entrySet().stream().forEach(
                company -> {
                    System.out.println(company.getKey());
                    company.getValue().stream().forEach(id -> System.out.printf("-- %s%n", id));
                }
        );
    }
}
