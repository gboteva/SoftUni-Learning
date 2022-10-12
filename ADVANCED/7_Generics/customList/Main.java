package customList;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        CustomList<String> customList = new CustomList<>();
        while (!input.equals("END")){
            String [] tokens = input.split("\\s+");
            String element = "";
            int index = -1;
            switch (tokens[0]){
                case "Add":
                    element = tokens[1];
                    customList.add(element);
                    break;
                case "Remove":
                    element = tokens[1];
                    index = Integer.parseInt(element);
                    customList.remove(index);
                    break;
                case "Contains":
                    element = tokens[1];
                    System.out.println(customList.contains(element));
                    break;
                case "Swap":
                    element = tokens[1];
                    index = Integer.parseInt(element);
                    int index2 = Integer.parseInt(tokens[2]);
                    customList.swap(index, index2);
                    break;
                case "Greater":
                    element = tokens[1];
                    System.out.println(customList.countGreaterThan(element));
                    break;
                case "Max":
                    System.out.println(customList.getMax());
                    break;
                case "Min":
                    System.out.println(customList.getMin());
                    break;
                case "Print":
                   customList.print();

                    break;
                case "Sort":
                    customList.sort();
                    break;

            }

            input = scanner.nextLine();
        }
    }
}
