package FundamentalsCourse.textProcessing_8.moreEx;

import java.util.Arrays;
import java.util.Scanner;

public class P03_TreasureFinder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] key = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        String text = scanner.nextLine();
        int keyLenght = key.length;

        while (!text.equals("find")){
            StringBuilder message = new StringBuilder();

            char [] arr = text.toCharArray();
            //ikegfp'jpne)bv=41P83X@
            int indexOfKey = 0;
            for (int i = 0; i < arr.length; i++) {
                char symbol = arr[i];

                if (indexOfKey>=key.length){
                   indexOfKey=0;
                }
                int value = key[indexOfKey];
                indexOfKey ++;

                char newSymbol = (char) (symbol -value);
                message.append(newSymbol);
            }

            int firstAmpersand = message.indexOf("&");
            int secondAmpersand = message.lastIndexOf("&");

            String treasure = message.substring(firstAmpersand+1, secondAmpersand);
            String coordinats = message.substring(message.indexOf("<")+1, message.indexOf(">"));
            System.out.printf("Found %s at %s%n", treasure, coordinats);
            keyLenght=key.length;
            text= scanner.nextLine();
        }
    }
}
