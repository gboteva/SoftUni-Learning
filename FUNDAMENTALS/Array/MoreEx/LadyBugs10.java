package FundamentalsCourse.arrays_3.moreEx;

import java.util.Arrays;
import java.util.Scanner;

public class LadyBugs10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());
        int []usedIdexes = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        String command = scanner.nextLine();

        int [] bugArray = new int[size];
        for (int i = 0; i < usedIdexes.length; i++) {
            int bugIndex = usedIdexes[i];
            if (bugIndex >= 0 && bugIndex < size) {
                bugArray[bugIndex] = 1;
            }
        }

        while (!command.equals("end")){
            int from =Integer.parseInt(command.split(" ")[0]);
            String direction = command.split(" ")[1];
            int flyLenght =Integer.parseInt(command.split(" ")[2]);

            if (from>=0 && from<=size-1 && bugArray[from]==1){
                bugArray[from]=0;
                if (direction.equals("right")){
                    if (from+flyLenght < size){
                        if (bugArray[from+flyLenght]==0){
                            bugArray[from+flyLenght]=1;
                        }else {
                            for (int i = from+flyLenght; i < bugArray.length ; i+=flyLenght) {
                                if (bugArray[i]==0){
                                    bugArray[i]=1;
                                    break;
                                }
                            }
                        }
                    }

                }else if (direction.equals("left")){
                    if (from-flyLenght >=0){
                        if (bugArray[from-flyLenght]==0){
                            bugArray[from-flyLenght]=1;
                        }else {
                            for (int i = from-flyLenght; i >=0 ; i-=flyLenght) {
                                if (bugArray[i]==0){
                                    bugArray[i]=1;
                                    break;
                                }
                            }
                        }
                    }
                }

            }

            command= scanner.nextLine();
        }

        for ( int bug : bugArray){
            System.out.print(bug + " ");
        }
    }
}

