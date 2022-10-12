package SetsAndMaps_03.ex;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class P06_FixEmails {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String data = scanner.nextLine();
        Map<String, String> emails = new LinkedHashMap<>();

        while (!data.equals("stop")){
            String name = data;
            String email = scanner.nextLine();
            if (!email.endsWith("us") && !email.endsWith("uk") && !email.endsWith("com")){
                emails.put(name,email);
            }

            data = scanner.nextLine();
        }
      emails.entrySet().forEach(e->
              System.out.printf("%s -> %s%n", e.getKey(), e.getValue()));
    }
}
