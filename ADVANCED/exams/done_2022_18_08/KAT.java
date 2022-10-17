package exams.august2022;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class KAT {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> plateList = Arrays.stream(scanner.nextLine().split(", "))
                .map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> carList = Arrays.stream(scanner.nextLine().split(", "))
                .map(Integer::parseInt).collect(Collectors.toList());

        ArrayDeque<Integer> plates = new ArrayDeque<>();
        plateList.forEach(plates::offer);

        ArrayDeque<Integer> cars = new ArrayDeque<>();
        carList.forEach(cars::push);

        int registeredCars = 0;
        int days = 0;

        while (!cars.isEmpty() && !plates.isEmpty()){
            days++;

            int countOfPlates = plates.poll();
            int countOfCars = cars.pop();
            int neededPlates = countOfCars * 2;

            if (neededPlates<= countOfPlates){
                //ако табелите стигат и/или остават
                registeredCars+=countOfCars;
                countOfPlates-=neededPlates;
                if (countOfPlates>0){
                    plates.offer(countOfPlates);
                }
            }else {
                //ако не стигат
                int carsToRegister = countOfPlates/2;
                registeredCars+=carsToRegister;
                int remainingCar = countOfCars - carsToRegister;
                if (remainingCar>0){
                    cars.add(remainingCar);
                }
            }
        }

        System.out.printf("%d cars were registered for %d days!%n", registeredCars, days);
        if (!plates.isEmpty()){
            System.out.printf("%d license plates remain!%n", plates.stream().mapToInt(e->e).sum());
        }

        if (!cars.isEmpty()){
            System.out.printf("%d cars remain without license plates!%n", cars.stream().mapToInt(e->e).sum());
        }
        if (plates.isEmpty() && cars.isEmpty()){
            System.out.println("Good job! There is no queue in front of the KAT!");
        }

    }
}
