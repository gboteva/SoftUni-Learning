package FundamentalsCourse.regex_9.ex;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P04_StarEnigma {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        String regex = "@(?<planet>[A-Za-z]*)[^@,!:>-]*:(?<population>[0-9]+)[^@,!:>-]*!(?<type>[AD])![^@,!:>-]*->(?<soldier>[0-9]+)";
        Pattern pattern = Pattern.compile(regex);
        List<String> attackedPlanets = new ArrayList<>();
        List<String> destroyedPlanets = new ArrayList<>();

        for (int i = 0; i <n ; i++) {
            String encriptedMessage = scanner.nextLine();
            String decripted = getDecripted(encriptedMessage);
            Matcher matcher = pattern.matcher(decripted);

            if (matcher.find()){
                String type = matcher.group("type");
                if (type.equals("A")){
                    attackedPlanets.add(matcher.group("planet"));
                }else if (type.equals("D")){
                   destroyedPlanets.add(matcher.group("planet"));
                }
            }
        }
        System.out.println("Attacked planets: " + attackedPlanets.size());
        attackedPlanets.stream().sorted().forEach(planet-> System.out.println("-> "+planet));
        System.out.println("Destroyed planets: " + destroyedPlanets.size());
        destroyedPlanets.stream().sorted().forEach(planet-> System.out.println("-> "+planet));
    }

    private static String getDecripted(String encriptedMessage) {
        int countSpecialLetters = getCountLetter(encriptedMessage);
        StringBuilder buildNewMessage = new StringBuilder();
        for ( char symbol:encriptedMessage.toCharArray() ){
            buildNewMessage.append((char)(symbol-countSpecialLetters));
        }
        return new String(buildNewMessage);
    }

    private static int getCountLetter(String encriptedMessage) {
        int count = 0;
        for ( char symbol:encriptedMessage.toLowerCase().toCharArray() ){
            if (symbol=='s'|| symbol=='t'|| symbol=='a'|| symbol=='r'){
                count++;
            }
        }
        return count;
    }
}
