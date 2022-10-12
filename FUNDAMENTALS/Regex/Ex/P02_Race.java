package FundamentalsCourse.regex_9.ex;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class P02_Race {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> racers = Arrays.stream(scanner.nextLine().split(", ")).collect(Collectors.toList());
        String data = scanner.nextLine();
        Map<String, Integer> ranklist = new LinkedHashMap<>();
        String regexLetter = "[A-Za-z]+";
        Pattern patternLetter = Pattern.compile(regexLetter);
        String regexDigit = "\\d";
        Pattern patternDigit = Pattern.compile(regexDigit);

        while (!data.equals("end of race")){
            Matcher matcherLetter = patternLetter.matcher(data);
            StringBuilder nameBuilder = new StringBuilder();
            while (matcherLetter.find()){
                nameBuilder.append(matcherLetter.group());
            }
            String racerName = nameBuilder.toString();

            Matcher matcherDigit = patternDigit.matcher(data);
            int speed = 0;
            while (matcherDigit.find()){
                speed+=Integer.parseInt(matcherDigit.group());
            }

            if (racers.contains(racerName)){
                if (ranklist.get(racerName)!=null){
                    ranklist.put(racerName, ranklist.get(racerName)+speed);
                }else {
                    ranklist.put(racerName, speed);
                }
            }
            data = scanner.nextLine();
        }

      //ranklist.entrySet().stream().sorted(Map.Entry.comparingByValue()); //- сортира по нарастващ ред

//     Map<String, Integer> firstThree = ranklist.entrySet().stream()
//             .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())) //до тук сортира в намаляващ ред
//             .limit(3)
//             .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a1, a2) -> a1, LinkedHashMap::new));
        List<String> firstThreeNames = ranklist.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(3)
                .map(entry->entry.getKey())
                .collect(Collectors.toList());

        System.out.println("1st place: " + firstThreeNames.get(0));
        System.out.println("2nd place: " + firstThreeNames.get(1));
        System.out.println("3rd place: " + firstThreeNames.get(2));

    }
}
