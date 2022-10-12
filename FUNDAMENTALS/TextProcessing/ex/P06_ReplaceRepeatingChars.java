package beforeCourse.textProcessing_8.ex;

import java.util.Scanner;

public class P06_ReplaceRepeatingChars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder build = new StringBuilder(scanner.nextLine());

        for (int i = 0; i <build.length() ; i++) {
            String currentSymbol = String.valueOf(build.charAt(i));
            //взимаме всеки символ и ако той не е последен и не съвпада със следващия минаваме на следваща итерация
            if (i<build.length()-1 && !currentSymbol.equals(String.valueOf(build.charAt(i+1)))){
                continue;
            }else { //ако съвпадат или сме на последна итерация
                if (i!=build.length()-1){
                    int firstIndex = -1;
                    int lastIndex = -1;
                    //ако текущия символ съвпада със следващия = намираме първи индекс
                    if (currentSymbol.equals(String.valueOf(build.charAt(i+1)))){
                        firstIndex=i;
                    }
                    //търсим последния:
                    for (int j = firstIndex+1; j < build.length(); j++) {
                        if (!String.valueOf(build.charAt(j)).equals(currentSymbol)){
                            lastIndex=j;
                            break;
                        }else if (j==build.length()-1 && currentSymbol.equals(String.valueOf(build.charAt(j)))){
                            lastIndex=j;
                        }
                    }
                    //заменяме поредицата с текущия символ;
                    if (i==build.length()-2 && currentSymbol.equals(String.valueOf(build.charAt(build.length()-1)))){
                        build.delete(firstIndex,lastIndex+1);
                        build.append(currentSymbol);
                    }else {
                        build.replace(firstIndex,lastIndex,currentSymbol);
                    }
                    i=-1;
                }
            }
        }
        System.out.println(build);
    }
}
