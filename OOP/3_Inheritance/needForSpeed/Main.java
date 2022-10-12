package needForSpeed;

public class Main {
    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle(15, 60);
        CrossMotorcycle crossMotorcycle = new CrossMotorcycle(5, 150);

        RaceMotorcycle raceMotorcycle = new RaceMotorcycle(30, 180);

        Car car = new Car(9, 110);
        Car sportCar = new SportCar(3232, 150);
    }
}
