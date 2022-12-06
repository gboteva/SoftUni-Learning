package garage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class GarageTests {
    private Car car;
    private Garage garage;

    @Before
    public void setup() {
        car = new Car("Opel", 100, 500.5);
        garage = new Garage();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetCars_ShouldReturnUnmodifiableList() {
        garage.getCars().add(car);
    }

    @Test
    public void testGetCars_ShouldReturnValidList() {
        garage.addCar(car);
        Assert.assertEquals(1, garage.getCars().size());
    }

    @Test
    public void testAddCar_ShouldReturnValidSizeOfList() {
        garage.addCar(car);
        Assert.assertEquals(1, garage.getCount());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddCar_ShouldThrowWhenCarIsNull(){
        garage.addCar(null);
    }

    @Test
    public void testFindAllCarsWithMaxSpeedAbove_ShouldReturnValidList() {
        garage.addCar(car);
        garage.addCar(new Car("Opel", 150, 1600));
        garage.addCar(new Car("Mazda", 160, 1600));
        int actual = garage.findAllCarsWithMaxSpeedAbove(100).size();
        Assert.assertEquals(2, actual);
    }

    @Test
    public void testGetTheMostExpensiveCar_ShouldReturnValidCar(){
        garage.addCar(car);
        garage.addCar(new Car("Opel", 150, 1600));
        garage.addCar(new Car("Mazda", 160, 1700));
        Car car = garage.getTheMostExpensiveCar();
        Assert.assertEquals("Mazda", car.getBrand());
    }

    @Test
    public void testGetTheMostExpensiveCar_ShouldReturnNullWhenListIsEmpty(){
        Assert.assertNull(garage.getTheMostExpensiveCar());
    }

    @Test
    public void testFindAllCarsByBrand_ShouldReturnValidList(){
        garage.addCar(car);
        garage.addCar(new Car("Opel", 150, 1600));
        garage.addCar(new Car("Mazda", 160, 1700));
        List<Car> cars = garage.findAllCarsByBrand("Opel");
        for (Car car1 : cars) {
            Assert.assertEquals(car1.getBrand(), "Opel");
        }
    }


}