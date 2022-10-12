package beforeCourse.textProcessing_8.ex;

import java.util.Scanner;

public class P07_StringExplosion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder string = new StringBuilder(scanner.nextLine());
        int morePower=0;

        for (int i = 0; i < string.length(); i++) {
            String currentSymbol = String.valueOf(string.charAt(i));
            if (currentSymbol.equals(">")){
                int power = Integer.parseInt(String.valueOf(string.charAt(i+1)));

                int powerIndex = string.indexOf(String.valueOf(string.charAt(i+1)));
                if (powerIndex==string.length()-1){
                    string.delete(powerIndex,powerIndex+1);
                }else {
                    power+=morePower;
                    String substringToDelete = "";
                    if (i+power>string.length()-1){
                        substringToDelete = string.substring(i+1);
                    }else {
                        substringToDelete = string.substring(i+1,i+power+1);
                    }

                    if (!substringToDelete.contains(">")){
                        string.delete(i+1,i+power+1);
                    }else {
                        int nextBombIndex = substringToDelete.indexOf(">")+i+1;
                        String deleteToNextBomb = string.substring(i+1,nextBombIndex);
                        string.delete(i+1,nextBombIndex);
                        morePower = power-deleteToNextBomb.length();
                    }
                }

            }
        }
        System.out.println(string);
    }
}
