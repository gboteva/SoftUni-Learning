package generics.customList;

import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String command = scanner.nextLine();
        CustomList<String> customList = new CustomList<>();

        while (!command.equals("END")){
            String[] tokens = command.split("\\s+");
            String action = tokens[0];

            switch (action){
                case "Add":
                    String element = tokens[1];
                    customList.add(element);
                    break;
                case "Remove":
                    int indexToRemove = Integer.parseInt(tokens[1]);
                    customList.remove(indexToRemove);
                    break;
                case "Contains":
                    String elementToCheck = tokens[1];
                    System.out.println(customList.contains(elementToCheck));
                    break;
                case "Swap":
                    int firstIndex = Integer.parseInt(tokens[1]);
                    int secondIndex = Integer.parseInt(tokens[2]);
                    customList.swap(firstIndex,secondIndex);
                    break;
                case "Greater":
                    String toCheck = tokens[1];
                    System.out.println(customList.countGreaterThan(toCheck));
                    break;
                case "Max":
                    System.out.println(customList.getMax());
                    break;
                case "Min":
                    System.out.println(customList.getMin());
                    break;
                case "Print":
                    Iterator<String> iterator = customList.iterator();
                    while (iterator.hasNext()){
                        System.out.println(iterator.next());
                    }
                    break;
                case "Sort":
                    Sorter.sort(customList);
                    break;
            }

            command = scanner.nextLine();
        }
    }
}
