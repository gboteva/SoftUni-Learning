package parking;

import java.util.ArrayList;
import java.util.List;

public class Parking {
    private String type;
    private int capacity;
    private List<Car> data;

    public Parking(String type, int capacity) {
        this.type = type;
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public void add(Car car) {
        if (this.capacity > this.data.size()) {
            data.add(car);
        }
    }

    public boolean remove(String manufacturer, String model) {
        int indexToRemove = -1;
        for (Car car : this.data) {
            if (car.getManufacturer().equals(manufacturer) && car.getModel().equals(model)) {
                indexToRemove = this.data.indexOf(car);
                break;
            }
        }
        if (indexToRemove >= 0 && indexToRemove < this.data.size()) {
            this.data.remove(indexToRemove);
            return true;
        } else {
            return false;
        }
    }

    public Car getLatestCar() {
        int maxYear = 0;
        int maxYearCarIndex = -1;
        for (Car car : this.data) {
            if (car.getYear() > maxYear) {
                maxYear = car.getYear();
                maxYearCarIndex = this.data.indexOf(car);
            }
        }
        if (maxYearCarIndex > -1) {
            return this.data.get(maxYearCarIndex);
        } else {
            return null;
        }
    }


    public Car getCar(String manufacturer, String model) {
        int indexToReturn = -1;
        for (Car car : this.data) {
            if (car.getManufacturer().equals(manufacturer) && car.getModel().equals(model)) {
                indexToReturn = this.data.indexOf(car);
                break;
            }
        }
        if (indexToReturn > -1) {
            return this.data.get(indexToReturn);
        } else {
            return null;
        }
    }

    public int getCount() {
        return this.data.size();
    }

    public String getStatistics(){
        StringBuilder builder = new StringBuilder();
        builder.append("The cars are parked in ").append(this.type) .append(":").append("\n");
        for (Car car : this.data ){
            builder.append(car).append("\n");
        }
        return builder.toString();
    }

}
