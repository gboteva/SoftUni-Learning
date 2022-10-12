package FundamentalsCourse.finalExam.exam02;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P02_DestinationMapper {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String regex = "([=\\/])(?<dest>[A-Z]{1}[A-Za-z]{2,})\\1";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        int points = 0;
        List<String> validDestinations = new ArrayList<>();
        while (matcher.find()){
            String destination = matcher.group("dest");
            points+=destination.length();
            validDestinations.add(destination);
        }
        System.out.print("Destinations: ");
        System.out.print(String.join(", ", validDestinations));
        System.out.println();
        System.out.println("Travel Points: "+points);
    }
}
