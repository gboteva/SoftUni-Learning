package beforeCourse.lists_5.moreEx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P05_DrumSet {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double savedMoney = Double.parseDouble(scanner.nextLine());
        List<Integer> initialQualityList = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        String command = scanner.nextLine();
        int initialSize = initialQualityList.size();
        int counter = 0;

        List<Integer> resultList = new ArrayList<>();
        for (int i = 0; i < initialQualityList.size(); i++) {
            resultList.add(initialQualityList.get(i));
        }

        while (!command.equals("Hit it again, Gabsy!")) {
            int hitPower = Integer.parseInt(command);

            newResultList(resultList, hitPower);

            for (int i = 0; i < resultList.size(); i++) {
                int currentDum = resultList.get(i);
                if (currentDum <= 0) {
                    double needMoney = initialQualityList.get(initialSize - resultList.size() - counter + i) * 3;
                    if (savedMoney >= needMoney) {
                        savedMoney -= needMoney;
                        resultList.add(resultList.indexOf(currentDum), initialQualityList.get(initialSize - resultList.size() - counter + i));
                        resultList.remove(i + 1);
                    } else {
                        if (resultList.indexOf(currentDum) == 0) {
                            counter++;
                        }
                        resultList.remove(resultList.indexOf(currentDum));
                        i -= 1;
                    }
                }
            }

            command = scanner.nextLine();
        }
        for (int element : resultList) {
            System.out.print(element + " ");
        }
        System.out.println();
        System.out.printf("Gabsy has %.2flv.", savedMoney);
    }

    private static void newResultList(List<Integer> resultList, int hitPower) {
        int currentResultSize = resultList.size();
        for (int i = 0; i < currentResultSize; i++) {
            resultList.add(resultList.get(i) - hitPower);
        }

        for (int i = 0; i < currentResultSize; i++) {
            resultList.remove(0);
        }
    }
}
