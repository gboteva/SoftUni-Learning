package FunctionalPrograming.ex;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P08_CustomComparator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> numbers = Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());

       Comparator<Integer> comparator = ((first, second)->{
           //първо четно, второ нечетно
          if (first%2==0 && (second%2)!=0){
              return -1; //да не ги разменя
          }

           //първо нечетно, второ четно
           if (first%2!=0 && second%2==0){
               return 1;   //да ги размени
           }

           //първо четно, второ четно
           //първо нечетно, второ нечетно
           else{
               return first.compareTo(second);
           }

       });
        numbers.stream().sorted(comparator).forEach(n-> System.out.print(n + " "));

    }
}

