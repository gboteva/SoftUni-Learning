package SetsAndMaps_03.ex;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class P05_Phonebook {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String data = scanner.nextLine();

        Map<String, String> phonebook = new HashMap<>();

        while (!data.equals("search")){
            String name = data.split("-")[0];
            String phoneNumber = data.split("-")[1];
            phonebook.put(name, phoneNumber);
            data= scanner.nextLine();
        }
        String name = scanner.nextLine();
        while (!name.equals("stop")){
            if (phonebook.containsKey(name)){
                String phoneNumber = phonebook.get(name);
                System.out.println (name + " -> " + phoneNumber);
            }else {
                System.out.println("Contact " + name + " does not exist.");
            }

            name = scanner.nextLine();
        }
    }
}
