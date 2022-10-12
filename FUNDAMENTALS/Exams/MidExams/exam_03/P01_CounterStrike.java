package FundamentalsCourse.midExamMid.exam_03;

import java.util.Scanner;

public class P01_CounterStrike {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        int initialEnergy = Integer.parseInt(scanner.nextLine());
        String command = scanner.nextLine();
        int energy = initialEnergy;
        int winCounter = 0;

        while (!command.equals("End of battle")){
            int distance = Integer.parseInt(command);

            if (energy>=distance){ //win
                energy-=distance;
                winCounter++;
                if (winCounter%3==0){
                    energy+=winCounter;
                }
            }else {
                System.out.printf("Not enough energy! Game ends with %d won battles and %d energy%n", winCounter, energy);
                break;
            }

            command= scanner.nextLine();
        }
        if (command.equals("End of battle")){
            System.out.printf("Won battles: %d. Energy left: %d", winCounter,energy);
        }

    }
}
