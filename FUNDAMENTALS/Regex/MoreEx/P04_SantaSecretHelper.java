package FundamentalsCourse.regex_9.moreEx;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P04_SantaSecretHelper {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int key = Integer.parseInt(scanner.nextLine());
        String name = scanner.nextLine();


        String  regex = "@(?<name>[A-Za-z]+)([^@!:>-])+(?<behavior>![GN]{1}!)";
        Pattern pattern =  Pattern.compile(regex);

        List<String> goodChildren = new ArrayList<>();

        while (!name.equals("end")){
            name = getEncriptedName(name, key);

            Matcher matcher = pattern.matcher(name);
            while (matcher.find()){
                String childName = matcher.group("name");
                String childBehavior = matcher.group("behavior");
                if (childBehavior.equals("!G!")){
                    goodChildren.add(childName);
                }
            }

            name=scanner.nextLine();
        }
        for ( String good : goodChildren ){
            System.out.println(good);
        }
    }

    private static String getEncriptedName(String name, int key) {
        StringBuilder encryptedMessage = new StringBuilder();
        for (int i = 0; i <name .length() ; i++) {
            encryptedMessage.append((char)(name.charAt(i)-key));
        }
        return  String.valueOf(encryptedMessage);
    }
}
