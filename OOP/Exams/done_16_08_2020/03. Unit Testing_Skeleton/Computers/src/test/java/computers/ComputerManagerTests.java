package computers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.List;

public class ComputerManagerTests {

    private Computer computer;
    private ComputerManager computerManager;

    @Before
    public void setup(){
        computer = new Computer("HP", "Pavilion", 1500);
        computerManager = new ComputerManager();
    }

    @Test (expected = UnsupportedOperationException.class)
    public void test_getComputers_shouldReturnUnmodifiableCollection(){
        computerManager.getComputers().add(computer);
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_addComputer_shouldThrowWhenTryToAddExistingComputer(){
        computerManager.addComputer(computer);
        computerManager.addComputer(computer);
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_addComputer_shouldThrowWhenTryToAddNull(){
        computerManager.addComputer(null);
    }

    @Test
    public void test_AddComputer_ShouldAdd(){
        computerManager.addComputer(computer);
        computerManager.addComputer(new Computer("Asus", "K", 2000));
        Assert.assertEquals(2, computerManager.getCount());
    }

    @Test
    public void test_getComputer_ShouldReturnValidComputer(){
        computerManager.addComputer(computer);
        computerManager.addComputer(new Computer("Asus", "K", 2000));

        Computer computer = computerManager.getComputer("Asus", "K");
        Assert.assertEquals(String.valueOf(2000.0),String.valueOf(computer.getPrice()));
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_getComputer_ShouldThrowWhenComputerDoesNotExists(){
        computerManager.addComputer(computer);
        computerManager.addComputer(new Computer("Asus", "K", 2000));

        computerManager.getComputer("Acer", "Y");
    }
    @Test (expected = IllegalArgumentException.class)
    public void test_getComputer_ShouldThrowWhenSearchedModelIsNull(){
        computerManager.addComputer(computer);
        computerManager.addComputer(new Computer("Asus", "K", 2000));

        computerManager.getComputer("Acer", null);
    }

    @Test
    public void test_removeComputer_ShouldRemove(){
        computerManager.addComputer(computer);
        computerManager.addComputer(new Computer("Asus", "K", 2000));

        computerManager.removeComputer("HP", "Pavilion");
        String manufac = computerManager.getComputers().get(0).getManufacturer();
        String model = computerManager.getComputers().get(0).getModel();
        Assert.assertEquals("Asus", manufac);
        Assert.assertEquals("K", model);
        Assert.assertEquals(1, computerManager.getCount());

    }

    @Test (expected = IllegalArgumentException.class)
    public void test_getComputersByManufacturer_ShouldThrowWhenGivenStringIsNull(){
        computerManager.getComputersByManufacturer(null);
    }

    @Test
    public void test_getComputersByManufacturer_ShouldReturnValidComputers(){
        computerManager.addComputer(computer);
        computerManager.addComputer(new Computer("Asus", "K", 2000));
        computerManager.addComputer(new Computer("HP", "Notebook", 4000));

        List<Computer> actual = computerManager.getComputersByManufacturer("HP");
        Assert.assertEquals(2, actual.size());

        for (Computer pc : actual) {
            Assert.assertEquals("HP", pc.getManufacturer());
        }
    }


}