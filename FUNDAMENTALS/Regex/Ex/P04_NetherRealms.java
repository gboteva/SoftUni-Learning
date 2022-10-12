package FundamentalsCourse.regex_9.ex;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P04_NetherRealms {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String [] input = scanner.nextLine().split("[, ]+");
        for (int i = 0; i <input.length ; i++) {
            int health = getHealth (input[i]);
            double demage = getDemage(input[i]);
            System.out.printf("%s - %d health, %.2f damage%n", input[i], health, demage);
        }
    }

    private static double getDemage(String name) {
        String regexDemage = "[\\+\\-]*\\d*\\.*\\d+";
        Pattern patternNumbers = Pattern.compile(regexDemage);
        Matcher matcherNumbers = patternNumbers.matcher(name);
        double demage = 0;
        while (matcherNumbers.find()){
            demage+=Double.parseDouble(matcherNumbers.group());
        }

        String regexMultyplyOrDivision = "[\\*\\/]";
        Pattern patternAction = Pattern.compile(regexMultyplyOrDivision);
        Matcher matcherAction = patternAction.matcher(name);
        while (matcherAction.find()){
            String sign = matcherAction.group();
            if (sign.equals("*")){
                demage*=2;
            }else if (sign.equals("/")){
                demage/=2;
            }
        }

        return demage;
    }

    private static int getHealth(String name) {
        String regexName = "[^0-9\\+\\*\\/\\-\\.]";
        Pattern patternName = Pattern.compile(regexName);
        Matcher matcherName = patternName.matcher(name);
        int health =0;
        while (matcherName.find()){
            health+=matcherName.group().charAt(0);
        }
        return health;
    }
}
