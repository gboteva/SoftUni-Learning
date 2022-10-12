package setsAndMaps;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Phonebook {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        String input = scanner.nextLine();
        Map<String, String> phonebook = new HashMap<>();

        while (!input.equals("search")){
            String [] info = input.split("-");
            phonebook.put(info[0], info[1]);
            input = scanner.nextLine();
        }

        String searchedName = scanner.nextLine();
        while (!searchedName.equals("stop")){
            if (phonebook.containsKey(searchedName)){
                System.out.println(searchedName + " -> " + phonebook.get(searchedName));

            }else {
                System.out.println("Contact " + searchedName + " does not exist.");
            }

            searchedName = scanner.nextLine();
        }
    }
}
