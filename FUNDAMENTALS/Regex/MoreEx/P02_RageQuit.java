package FundamentalsCourse.regex_9.moreEx;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class P02_RageQuit {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();

        List<String> text = Arrays.stream(inputLine.split("\\d+")).collect(Collectors.toList());
        List<Integer> countRepeat = new ArrayList<>();
        String regex = "[0-9]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputLine);

        while (matcher.find()) {
            int num = Integer.parseInt(matcher.group());
            countRepeat.add(num);
        }

        List<String> repeatedStrings = new ArrayList<>();
        List<Character> uniqueSymbols = new ArrayList<>();

        for (int i = 0; i < text.size(); i++) {
            String currentText = text.get(i).toUpperCase();
            int currentTimes = countRepeat.get(i);



            StringBuilder currentWord = new StringBuilder();
            for (int j = 0; j < currentTimes; j++) {
                currentWord.append(currentText);
            }
            repeatedStrings.add(String.valueOf(currentWord));


            for (int j = 0; j < currentText.length(); j++) {
                char currentSymbol = currentText.charAt(j);
                if (uniqueSymbols.isEmpty()) {
                    uniqueSymbols.add(currentSymbol);
                } else {
                    if (!uniqueSymbols.contains(currentSymbol) && currentTimes!=0){ //ако повтаряемостта е 0, не трябва да се отчитат уикални символи
                        uniqueSymbols.add(currentSymbol);
                    }
                }
            }
        }

        System.out.printf("Unique symbols used: %d%n",uniqueSymbols.size());
        for (String word : repeatedStrings){
            System.out.print(word);
        }
    }
}
