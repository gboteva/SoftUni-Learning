package exams.june2022;

import java.awt.*;
import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ItsChocolateTime {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Double> milkList = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Double::parseDouble).collect(Collectors.toList());
        List<Double> cacaoList = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Double::parseDouble).collect(Collectors.toList());

        ArrayDeque<Double> milk = new ArrayDeque<>();
        milkList.forEach(milk::offer);

        ArrayDeque<Double> cacao = new ArrayDeque<>();
        cacaoList.forEach(cacao::push);

        int milkChocolate = 0;
        int darkChocolate = 0;
        int bakingChocolate = 0;

        while (!milk.isEmpty() && !cacao.isEmpty()) {

            double milkQuantity = milk.poll();
            double cacaoQuantity = cacao.pop();
            double percentage = cacaoQuantity / (milkQuantity + cacaoQuantity) * 100;

            if (percentage == 30) {
                milkChocolate++;
            } else if (percentage == 50) {
                darkChocolate++;
            } else if (percentage == 100) {
                bakingChocolate++;
            } else {
                milkQuantity += 10;
                milk.offer(milkQuantity);
            }

        }

        if (milkChocolate >= 1 && darkChocolate >= 1 && bakingChocolate >= 1) {
            System.out.println("It’s a Chocolate Time. All chocolate types are prepared.");
        } else {
            System.out.println("Sorry, but you didn't succeed to prepare all types of chocolates.");
        }

        if (bakingChocolate > 0) {
            System.out.println("# Baking Chocolate --> " + bakingChocolate);
        }

        if (darkChocolate > 0){
            System.out.println("# Dark Chocolate --> " + darkChocolate);
        }

        if (milkChocolate>0){
            System.out.println("# Milk Chocolate --> " + milkChocolate);
        }


    }
}
