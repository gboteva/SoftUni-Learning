package Lab.p04_HotelReservation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] inputLine = scanner.nextLine().split("\\s+");

        double pricePerDay = Double.parseDouble(inputLine[0]);
        int numberOfDays = Integer.parseInt(inputLine[1]);
        Season currentSeason = Season.valueOf(inputLine[2]);
        DiscountType discount = DiscountType.valueOf(inputLine[3]);

        double result = PriceCalculator.calculatePrice(pricePerDay, numberOfDays, currentSeason, discount);
        System.out.printf("%.2f", result);

    }
}
