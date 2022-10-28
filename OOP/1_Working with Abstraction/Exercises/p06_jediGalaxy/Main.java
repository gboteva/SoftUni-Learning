package jediGalaxy;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        Galaxy galaxy = new Galaxy(readDimensionsFromTheConsole(scanner));


        String command = scanner.nextLine();


        while (!command.equals("Let the Force be with you")) {

            int[] jediDimensions = Arrays.stream(command.split("\\s+")).mapToInt(Integer::parseInt).toArray();

            galaxy.getEvilPower().move(galaxy.getField(), readDimensionsFromTheConsole(scanner));


            galaxy.getJedi().move(galaxy.getField(), jediDimensions);

            command = scanner.nextLine();
        }

        System.out.println(galaxy.getJedi().getCollectedStars());


    }

    private static int[] readDimensionsFromTheConsole(Scanner scanner) {
        return Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
    }


}
