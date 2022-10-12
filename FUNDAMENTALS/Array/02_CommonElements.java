package Array_Ex;

import java.util.Scanner;

public class CommonElements_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String [] firstArr = scanner.nextLine().split(" ");
        String [] secondArr = scanner.nextLine().split(" ");

        for (int index = 0; index < secondArr.length ; index++) {
            for (int i = 0; i < firstArr.length ; i++) {
                if (secondArr[index].equals(firstArr[i])){
                    System.out.print(secondArr[index]+ " ");
                }
            }
        }
    }
}
