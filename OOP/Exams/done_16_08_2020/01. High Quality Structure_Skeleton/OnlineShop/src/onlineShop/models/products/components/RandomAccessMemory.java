package onlineShop.models.products.components;

public class RandomAccessMemory extends BaseComponent{
    private final static double multiplier = 1.20;

    public RandomAccessMemory(int id, String manufacturer, String model, double price, double overallPerformance, int generation) {
        super(id, manufacturer, model, price, overallPerformance * multiplier, generation);
    }
}
