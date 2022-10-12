package exams.february2022;

import java.util.*;

public class FoodFinder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] vow = scanner.nextLine().split("\\s+");
        String[] con = scanner.nextLine().split("\\s+");
        ArrayDeque<String> vowel = new ArrayDeque<>();
        Arrays.stream(vow).forEach(vowel::offer);
        ArrayDeque<String> consonant = new ArrayDeque<>();
        Arrays.stream(con).forEach(consonant::push);

        List<char[]> words = new ArrayList<>();
        words.add("pear".toCharArray());
        words.add("flour".toCharArray());
        words.add("pork".toCharArray());
        words.add("olive".toCharArray());

        List<String> letters = new ArrayList<>();
        List<String> foundedWords = new ArrayList<>();
        while (!consonant.isEmpty()) {
            String vowelLetter = vowel.poll();
            String consonantLetter = consonant.pop();

            for (char[] word : words) {
                if (wordContainsLetter(word, vowelLetter)) {
                    letters.add(vowelLetter);
                }
                if (wordContainsLetter(word, consonantLetter)) {
                    letters.add(consonantLetter);
                }
            }
            vowel.offer(vowelLetter);

        }
        for (char[] word : words) {
            List<String> wordToString = getList(word);
            if (letters.containsAll(wordToString)) {
                foundedWords.add(getWord(word));
                letters = removeFoundedLetter(word, letters);
            }
        }

        System.out.println("Words found: " + foundedWords.size());
        foundedWords.forEach(System.out::println);

    }

    private static List<String> removeFoundedLetter(char[] word, List<String> letters) {
        for (char c : word) {
            letters.remove(String.valueOf(c));
        }
        return letters;
    }


    private static String getWord(char[] word) {
        StringBuilder builder = new StringBuilder();
        for (char c : word) {
            builder.append(c);
        }
        return builder.toString();
    }

    private static List<String> getList(char[] word) {
        List<String> wordToChar = new ArrayList<>();
        for (char c : word) {
            wordToChar.add(String.valueOf(c));
        }
        return wordToChar;
    }

    private static boolean wordContainsLetter(char[] word, String vowelLetter) {
        for (char c : word) {
            if (vowelLetter.equals(String.valueOf(c))) {
                return true;
            }
        }
        return false;
    }
}
