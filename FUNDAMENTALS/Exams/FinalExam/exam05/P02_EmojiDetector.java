package FundamentalsCourse.finalExam.exam05;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P02_EmojiDetector {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        String regexValidEmoji = "([:]{2}|[*]{2})([A-Z][a-z]{2,})\\1";
        Pattern patternValidEmodji = Pattern.compile(regexValidEmoji);

        String regexDigit = "[\\-]?\\d{1}";
        Pattern patternDigit = Pattern.compile(regexDigit);
        Matcher matcherDigit = patternDigit.matcher(text);

        BigInteger coolThreshold = new BigInteger(String.valueOf(1));
        while (matcherDigit.find()){
            int currentDigit = Integer.parseInt(matcherDigit.group());
            coolThreshold = coolThreshold.multiply(BigInteger.valueOf(currentDigit));
        }
        System.out.printf("Cool threshold: %d%n", coolThreshold);

        Matcher matcherEmoji = patternValidEmodji.matcher(text);
        List<String> allEmoji = new ArrayList<>();
        while (matcherEmoji.find()){
            allEmoji.add(matcherEmoji.group());
        }
        System.out.printf("%d emojis found in the text. The cool ones are:%n", allEmoji.size());

        for (String emoji : allEmoji ){
            long sum = 0;
            for (int i = 0; i < emoji.length(); i++) {
                if (Character.isLetter(emoji.charAt(i))){
                    sum+=emoji.charAt(i);
                }
            }
            if (coolThreshold.compareTo(BigInteger.valueOf(sum))<1){
                System.out.println(emoji);
            }
        }
    }
}
