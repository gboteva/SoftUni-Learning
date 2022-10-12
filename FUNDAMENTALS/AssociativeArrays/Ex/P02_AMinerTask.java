package beforeCourse.AssociativeArrays_7.ex;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class P02_AMinerTask {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Map<String, Integer> resources = new LinkedHashMap<>();
        while (!input.equals("stop")){
            String resource = input;
            int quantity = Integer.parseInt(scanner.nextLine());

            if (!resources.containsKey(resource)){
                resources.put(resource, quantity);
            }else {
                resources.put(resource, resources.get(resource)+quantity);
            }

            input = scanner.nextLine();
        }
        resources.entrySet().forEach(entry-> System.out.printf("%s -> %d%n", entry.getKey(), entry.getValue()));
    }
}
