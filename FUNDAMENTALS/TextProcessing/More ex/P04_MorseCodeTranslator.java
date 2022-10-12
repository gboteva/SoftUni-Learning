package FundamentalsCourse.textProcessing_8.moreEx;

import java.util.Scanner;

public class P04_MorseCodeTranslator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] morseCode = scanner.nextLine().split(" \\| ");
        StringBuilder message = new StringBuilder();

        for (int i = 0; i < morseCode.length ; i++) {
                String currentWord = morseCode[i];
                String [] splitWord = morseCode[i].split(" ");
                for (int j = 0; j < splitWord.length ; j++) {
                String letter = splitWord[j];
                switch (letter){
                    case ".-": message.append("A");
                    break;
                    case "-...": message.append("B");
                    break;
                    case "-.-.": message.append("C");
                    break;
                    case "-..": message.append("D");
                    break;
                    case ".": message.append("E");
                        break;
                    case "..-.": message.append("F");
                        break;
                    case "--.": message.append("G");
                        break;
                    case "....": message.append("H");
                        break;
                    case "..": message.append("I");
                        break;
                    case ".---": message.append("J");
                        break;
                    case "-.-": message.append("K");
                        break;
                    case ".-..": message.append("L");
                        break;
                    case "--": message.append("M");
                        break;
                    case "-.": message.append("N");
                        break;
                    case "---": message.append("O");
                        break;
                    case ".--.": message.append("P");
                        break;
                    case "--.-": message.append("Q");
                        break;
                    case ".-.": message.append("R");
                        break;
                    case "...": message.append("S");
                        break;
                    case "-": message.append("T");
                        break;
                    case "..-": message.append("U");
                        break;
                    case "...-": message.append("V");
                        break;
                    case ".--": message.append("W");
                        break;
                    case "-..-": message.append("X");
                        break;
                    case "-.--": message.append("Y");
                        break;
                    case "--..": message.append("Z");
                        break;

                }
            }
                message.append(" ");
        }
        System.out.println(message);
    }
}
