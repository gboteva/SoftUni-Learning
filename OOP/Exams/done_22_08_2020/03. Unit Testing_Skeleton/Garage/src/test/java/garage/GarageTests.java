package garage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class GarageTests {
    private Car car;
    private Car car2;
    private Garage garage;

    @Before
    public void setup(){
        this.car = new Car("Toyota", 200, 3000);
        this.car2 = new Car("Seat", 100, 2000);
        this.garage = new Garage();
    }

    @Test (expected = UnsupportedOperationException.class)
    public void test_getGars_ShouldReturnUnmodifiableList(){
        this.garage.getCars().add(car);
    }

    @Test
    public void test_getCount_ShouldReturnValidCount(){
        garage.addCar(car);
        garage.addCar(car2);
        Assert.assertEquals(2, garage.getCount());
    }


    @Test (expected = IllegalArgumentException.class)
    public void test_addCar_ShouldThrowIfGivenCarIsNull(){
        this.garage.addCar(null);
    }

    @Test
    public void test_addCar_ShouldAddValidCarSuccessful(){
        garage.addCar(car);
        Assert.assertEquals(1, garage.getCount());
        Assert.assertEquals("Toyota", garage.getCars().get(0).getBrand());
        Assert.assertEquals(200, garage.getCars().get(0).getMaxSpeed());
        Assert.assertEquals("3000.0",String.valueOf(garage.getCars().get(0).getPrice()));
    }

    @Test
    public void test_findAllCarsWithMaxSpeedAbove_shouldReturnValidCar(){
        garage.addCar(car);
        garage.addCar(car2);
        List<Car> maxSpeedCar = garage.findAllCarsWithMaxSpeedAbove(150);
        Assert.assertEquals(1, maxSpeedCar.size());
        Assert.assertEquals("Toyota", maxSpeedCar.get(0).getBrand());
    }

    @Test
    public void test_getTheMostExpensiveCar_shouldReturnValidCar(){
        garage.addCar(car);
        garage.addCar(car2);
        Car mostExpensiveCar = garage.getTheMostExpensiveCar();
        Assert.assertEquals("Toyota", mostExpensiveCar.getBrand());
    }

    @Test
    public void test_getMostExpensiveCar_shouldReturnNullIfGarageIsEmpty(){
        Assert.assertNull(garage.getTheMostExpensiveCar());
    }

    @Test
    public void test_findAllCarsByBrand_shouldReturnValidCar(){
        garage.addCar(car);
        garage.addCar(car2);
        List<Car> carsByBrand = garage.findAllCarsByBrand("Toyota");
        Assert.assertEquals(1, carsByBrand.size());
        Assert.assertEquals("Toyota", carsByBrand.get(0).getBrand());
    }

}