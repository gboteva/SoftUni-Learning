package dealership;

import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private String name;
    private int capacity;
    private List<Car> data;

    public Dealership(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        data = new ArrayList<>();
    }

    public void add (Car car){
        if (this.data.size()<capacity){
            this.data.add(car);
        }
    }

    public boolean buy (String manufacturer, String model){
        int indexToRemove = -1;
        for (int i = 0; i < this.data.size(); i++) {
            if (this.data.get(i).getManufacturer().equals(manufacturer)
            && this.data.get(i).getModel().equals(model)){
                indexToRemove = i;
                break;
            }
        }
        if (indexToRemove>-1){
            this.data.remove(indexToRemove);
            return true;
        }else {
            return false;
        }
    }

    public Car getLatestCar(){
        int maxYear = 0;
        Car latest = null;
        for (Car c : this.data ){
            if (c.getYear()>maxYear){
                maxYear = c.getYear();
                latest = c;
            }
        }
        return latest;
    }

    public Car getCar (String manufacturer, String model){
        for (int i = 0; i < this.data.size(); i++) {
            if (this.data.get(i).getManufacturer().equals(manufacturer)
                    && this.data.get(i).getModel().equals(model)){
               return this.data.get(i);
            }
        }
        return null;
    }

    public int getCount (){
        return this.data.size();
    }

    public String getStatistics(){
        StringBuilder builder = new StringBuilder();
        builder.append("The cars are in a car dealership ").append(this.name).append(":");
        for ( Car car: this.data ){
            builder.append("\n").append(car);
        }
        return builder.toString();
    }

}
