package beforeCourse.lists_5.ex;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P03_HouseParty {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int countCommands = Integer.parseInt(scanner.nextLine());
        List<String> guestList = new ArrayList<>();

        for (int i = 0; i <countCommands ; i++) {
            String command = scanner.nextLine();

            if (!command.contains("not going")){
                String guestNameIn = command.split(" ")[0];
                if (guestList.isEmpty()){
                    guestList.add(guestNameIn);
                }else {
                    boolean isInTheList = false;
                    for (int j = 0; j <guestList.size() ; j++) {
                        if (guestNameIn.equals(guestList.get(j))){
                            System.out.printf("%s is already in the list!%n", guestNameIn);
                            isInTheList=true;
                            break;
                        }
                    }
                    if (!isInTheList){
                        guestList.add(guestNameIn);
                    }
                }

            }else {
                String guestNameOut = command.split(" ")[0];
                boolean isRemoved = false;
                for (int j = 0; j < guestList.size() ; j++) {
                    if (guestNameOut.equals(guestList.get(j))){
                        guestList.remove(guestNameOut);
                        isRemoved=true;
                        break;
                    }
                }
                if (!isRemoved){
                    System.out.printf("%s is not in the list!%n", guestNameOut);
                }
            }
        }
        for ( String guest: guestList ){
            System.out.println(guest);
        }
    }
}
