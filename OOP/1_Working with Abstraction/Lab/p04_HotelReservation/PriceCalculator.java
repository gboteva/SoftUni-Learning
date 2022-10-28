package Lab.p04_HotelReservation;

public class PriceCalculator {
    public static double calculatePrice (double pricePerDay, int numberOfDays, Season season, DiscountType discountType){
        int multiplier = season.getMultiplier();
        double discountMultiplier = discountType.getDiscount() / 100.00;
        double priceBeforeDiscount = numberOfDays * pricePerDay * multiplier;
        double discountAmount = priceBeforeDiscount * discountMultiplier;
        return priceBeforeDiscount-discountAmount;
    }

}
