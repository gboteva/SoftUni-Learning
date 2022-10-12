package FundamentalsCourse.finalExam.exam03;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P02_MirrorWords {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String regex = "([@#])(?<wordOne>[A-Za-z]{3,})\\1{2}(?<wordTwo>[A-Za-z]{3,})\\1";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        List<String> pairs = new ArrayList<>();

        while (matcher.find()){
            String firstWord = matcher.group("wordOne");
            String secondWord = matcher.group("wordTwo");
            pairs.add(firstWord);
            pairs.add(secondWord);
        }
        if (pairs.size()==0){
            System.out.println("No word pairs found!");
        }else {
            System.out.println(pairs.size()/2 + " word pairs found!");
        }

        List<String> mirrorWord = new ArrayList<>();
        for (int i = 0; i <pairs.size() ; i+=2) {
            String firstWord = pairs.get(i);
            String secondWord = pairs.get(i+1);
            StringBuilder reversed =new StringBuilder();
            String secondReversedWord = String.valueOf(reversed.append(secondWord).reverse());
            if (firstWord.equals(secondReversedWord)){
                mirrorWord.add(firstWord);
                mirrorWord.add(secondWord);
            }
        }
        if (mirrorWord.size()==0){
            System.out.println("No mirror words!");
        }else {
            System.out.println("The mirror words are:");
            for (int i = 0; i <mirrorWord.size() ; i+=2) {
                if (i< mirrorWord.size()-2){
                    System.out.print(mirrorWord.get(i)+ " <=> " + mirrorWord.get(i+1)+", ");
                }else {
                    System.out.println(mirrorWord.get(i) + " <=> " + mirrorWord.get(i+1));
                }
            }
        }
    }
}
