package FundamentalsCourse.finalExam.exam02;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P01_WorldTour {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
       String stops = scanner.nextLine();
       String command = scanner.nextLine();

       while (!command.equals("Travel")){
           String action = command.split(":")[0];
           switch (action){
               case "Add Stop":
                   int index = Integer.parseInt(command.split(":")[1]);
                   String destination = command.split(":")[2];
                   if (index>=0 && index<stops.length()){               // na 3 index -> stringLenght = 4
                       String firstHalf = stops.substring(0,index);  // 0-2
                       String secondHalf = stops.substring(index);
                       StringBuilder newString = new StringBuilder();
                       newString.append(firstHalf);
                       newString.append(destination);
                       newString.append(secondHalf);
                       stops = String.valueOf(newString);
                       System.out.println(stops);
                   }else {
                       System.out.println(stops);
                   }
                   break;
               case "Remove Stop":
                   int startIndex = Integer.parseInt(command.split(":")[1]);
                   int endIndex = Integer.parseInt(command.split(":")[2]);
                   if (startIndex>=0 && startIndex<stops.length() && endIndex>=0 && endIndex<stops.length()){
                       String firstHalf = stops.substring(0,startIndex);
                       String secondHalf = stops.substring(endIndex+1);
                       StringBuilder newString = new StringBuilder();
                       newString.append(firstHalf);
                       newString.append(secondHalf);
                       stops = String.valueOf(newString);
                       System.out.println(stops);
                   }else {
                       System.out.println(stops);
                   }
                   break;
               case "Switch":
                   String oldDestination = command.split(":")[1];
                   String newDestination = command.split(":")[2];
                   if (stops.contains(oldDestination)){
                       stops = stops.replace(oldDestination,newDestination);
                       System.out.println(stops);
                   }else {
                       System.out.println(stops);
                   }
                   break;
           }

           command= scanner.nextLine();
       }
        System.out.printf("Ready for world tour! Planned stops: %s", stops);
    }
}
