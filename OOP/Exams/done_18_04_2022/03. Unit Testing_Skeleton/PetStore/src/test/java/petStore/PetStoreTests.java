package petStore;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PetStoreTests {

    private Animal animal;
    private PetStore petStore;

    @Before
    public void setup(){
        this.animal = new Animal("cat", 10, 100);
        petStore = new PetStore();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetAnimals_ShouldReturnUnmodifiableList(){
        List<Animal> actual = petStore.getAnimals();
        actual.add(animal);
    }

    @Test
    public void testGetCount_ShouldReturnRealSizeOfList(){
        petStore.addAnimal(animal);
        Assert.assertEquals(1, petStore.getCount());
    }

    @Test
    public void testFindAllAnimalWithGivenKg_ShouldReturnValidList(){
        petStore.addAnimal(animal);
        petStore.addAnimal(new Animal("dog", 10, 300));
        List<Animal> actual = petStore.findAllAnimalsWithMaxKilograms(9);

        Assert.assertEquals(2, actual.size());
    }

    @Test
    public void testAddAnimal_ShouldAddGivenAnimal(){
        petStore.addAnimal(animal);
        Assert.assertEquals("cat", petStore.getAnimals().get(0).getSpecie());
        Assert.assertEquals("10",String.valueOf(petStore.getAnimals().get(0).getMaxKilograms()));
        Assert.assertEquals("100.0",String.valueOf(petStore.getAnimals().get(0).getPrice()));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddAnimal_ShouldThrowWhenAnimalIsNull(){
        petStore.addAnimal(null);
    }

    @Test
    public void testSetAge_ShouldSetAgeOfExistingAnimal(){
        animal.setAge(5);
        Assert.assertEquals(5, animal.getAge());
    }

    @Test
    public void testGetMostExpensiveAnimal_ShouldReturnValidAnimal(){
        petStore.addAnimal(animal);
        petStore.addAnimal(new Animal("dog", 30, 500 ));

        Assert.assertEquals("dog", petStore.getTheMostExpensiveAnimal().getSpecie());
    }

    @Test
    public void testGetMostExpensiveAnimal_ShouldReturnNullWhenListIsEmpty(){
        Assert.assertNull(petStore.getTheMostExpensiveAnimal());
    }

    @Test
    public void testFindAnimalBySpecie_ShouldReturnValidList(){
        petStore.addAnimal(animal);
        petStore.addAnimal(new Animal("cat", 5, 30));
        Assert.assertEquals(2, petStore.findAllAnimalBySpecie("cat").size());
    }


}

