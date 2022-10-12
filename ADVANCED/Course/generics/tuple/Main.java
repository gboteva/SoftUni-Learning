package generics.tuple;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] tokens = scanner.nextLine().split("\\s+");
        String name = tokens[0] + " " + tokens[1];
        String address = tokens[2];
        String town = tokens[3];
        ThreeTuple<String, String, String> tuple = new ThreeTuple<>(name, address, town);
        System.out.println(tuple);

        String[] tokens2 = scanner.nextLine().split("\\s+");
        String firstName = tokens2[0];
        int num = Integer.parseInt(tokens2[1]);
        String drunk = tokens2[2];
        String third = "";
        if (drunk.equals("drunk")){
            third = "true";
        }else {
            third = "false";
        }
        ThreeTuple<String, Integer, String> tuple2 = new ThreeTuple<>(firstName, num, third);
        System.out.println(tuple2);


        String[] tokens3 = scanner.nextLine().split("\\s+");
        String something = tokens3[0];
        double number = Double.parseDouble(tokens3[1]);
        String bank = tokens3[2];

        ThreeTuple<String, Double, String> tuple3 = new ThreeTuple<>(something, number, bank);
        System.out.println(tuple3);
    }
}
