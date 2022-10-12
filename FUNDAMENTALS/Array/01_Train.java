package Array_Ex;

import java.util.Scanner;

public class Train_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int countWagons = Integer.parseInt(scanner.nextLine());
        int [] peopleInTrain = new int [countWagons];
        int allPeople = 0;
        for (int i = 0; i <countWagons ; i++) {
            int countPeople = Integer.parseInt(scanner.nextLine());
            allPeople+=countPeople;
            peopleInTrain[i] = countPeople;
        }
        for ( int count:peopleInTrain ){
            System.out.print(count + " ");
        }
        System.out.println();
        System.out.println(allPeople);
    }
}
