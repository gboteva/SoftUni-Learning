package FundamentalsCourse.midExamMid.exam_06;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P03_ManOWar {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> pirateShip = Arrays.stream(scanner.nextLine().split(">")).map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> warShip = Arrays.stream(scanner.nextLine().split(">")).map(Integer::parseInt).collect(Collectors.toList());
        int maxHealth = Integer.parseInt(scanner.nextLine());
        String command = scanner.nextLine();
        boolean isSunken = false;

        while (!command.equals("Retire")) {
            String action = command.split(" ")[0];
            switch (action) {
                case "Fire":
                    int index = Integer.parseInt(command.split(" ")[1]);
                    int demage = Integer.parseInt(command.split(" ")[2]);
                    if (index >= 0 && index < warShip.size()) {
                        if (warShip.get(index) > 0) {
                            warShip.add(index, warShip.get(index) - demage);
                            warShip.remove(index + 1);
                            if (warShip.get(index) <= 0) {
                                System.out.println("You won! The enemy ship has sunken.");
                                isSunken=true;
                                break;
                            }
                        } else {
                            System.out.println("You won! The enemy ship has sunken.");
                            break;
                        }
                    }
                    break;
                case "Defend":
                    int startIndex = Integer.parseInt(command.split(" ")[1]);
                    int endIndex = Integer.parseInt(command.split(" ")[2]);
                    int demageDefend = Integer.parseInt(command.split(" ")[3]);
                    if (startIndex >= 0 && startIndex < pirateShip.size() && endIndex >= 0 && endIndex < pirateShip.size()) {
                        for (int i = startIndex; i <= endIndex; i++) {
                            if (pirateShip.get(i) > 0) {
                                pirateShip.add(i, pirateShip.get(i) - demageDefend);
                                pirateShip.remove(i + 1);
                                if (pirateShip.get(i) <= 0) {
                                    System.out.println("You lost! The pirate ship has sunken.");
                                    isSunken=true;
                                    break;
                                }
                            } else {
                                System.out.println("You lost! The pirate ship has sunken.");
                                break;
                            }
                        }
                    }

                    break;
                case "Repair":
                    int repairIndex = Integer.parseInt(command.split(" ")[1]);
                    int health = Integer.parseInt(command.split(" ")[2]);
                    if (repairIndex>=0 && repairIndex< pirateShip.size()){
                        if (pirateShip.get(repairIndex)>=0) {
                            if (pirateShip.get(repairIndex) + health >= maxHealth) {
                                pirateShip.add(repairIndex, maxHealth);
                                pirateShip.remove(repairIndex + 1);
                            } else {
                                pirateShip.add(repairIndex, pirateShip.get(repairIndex) + health);
                                pirateShip.remove(repairIndex + 1);
                            }
                        }
                    }
                    break;
                case "Status":
                    int countSectionsForRepire = 0;
                    for (int i = 0; i <pirateShip.size() ; i++) {
                        if (pirateShip.get(i)<(maxHealth*0.2)){
                            countSectionsForRepire++;
                        }
                    }
                    System.out.printf("%d sections need repair.%n", countSectionsForRepire);
                    break;
            }
                if (isSunken){
                    break;
                }
            command = scanner.nextLine();
        }

        int pirateShipSum = 0;
        boolean isContainsNegativPirates = false;
        for (int i = 0; i < pirateShip.size() ; i++) {
            pirateShipSum+=pirateShip.get(i);
            if (pirateShip.get(i)<=0){
                isContainsNegativPirates=true;
            }
        }

        int warShipSum = 0;
        boolean isContainsNegativWar = false;
        for (int i = 0; i <warShip.size() ; i++) {
            warShipSum+=warShip.get(i);
            if (warShip.get(i)<=0){
                isContainsNegativWar = true;
            }
        }

        if (!isContainsNegativPirates && !isContainsNegativWar){
            System.out.printf("Pirate ship status: %d%n", pirateShipSum);
            System.out.printf("Warship status: %d%n", warShipSum);
        }
    }
}
