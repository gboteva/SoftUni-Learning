package lab_p02_carShopExtend;

public class Seat extends CarImpl implements Sellable {
    private Double price;

    public Seat(String model, String color, Integer horsePower, String countryProduced, Double price) {
        super(model, color, horsePower, countryProduced);
        this.price = price;
    }

    @Override
    public Double getPrice() {
        return this.price;
    }

    public String toString (){
        StringBuilder builder = new StringBuilder();
        builder.append(super.toString()).append("\n");
        builder.append(String.format("%s sells for %.6f", getModel(), getPrice()));
        return builder.toString();
    }




}
