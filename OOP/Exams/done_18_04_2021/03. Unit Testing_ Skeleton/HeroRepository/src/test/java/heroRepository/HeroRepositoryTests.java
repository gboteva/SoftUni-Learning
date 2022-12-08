package heroRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.nio.file.NotLinkException;

public class HeroRepositoryTests {

    private Hero hero;
    private HeroRepository heroRepository;
    private Hero hero2;
    private Hero hero3;
    private Hero hero4;

    @Before
    public void setup(){
        this.hero = new Hero("Gosho", 2);
        this.heroRepository = new HeroRepository();
        hero2 = new Hero("Ivan", 4);
        hero3 = new Hero("Toshko", 10);
        hero4 = new Hero("Ogi", 10);
    }

    @Test
    public void test_getCount_ShouldReturnValidCount(){
        Assert.assertEquals(0, heroRepository.getCount());
        heroRepository.create(hero);
        Assert.assertEquals(1, heroRepository.getCount());
    }

    @Test (expected = NullPointerException.class)
    public void test_create_ShouldThrowWhenHeroIsNull(){
        heroRepository.create(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_create_ShouldThrowWhenHeroAlradyExist(){
        heroRepository.create(hero);
        heroRepository.create(hero);
    }

    @Test
    public void test_create_ShouldAddWalidHero(){
        String actual = heroRepository.create(hero);
        Assert.assertEquals(1, heroRepository.getCount());
        Assert.assertEquals("Gosho", heroRepository.getHero("Gosho").getName());
        String expected = "Successfully added hero Gosho with level 2";
        Assert.assertEquals(expected, actual);
    }

    @Test (expected = NullPointerException.class)
    public void test_Remove_ShouldThrowWhenNameIsNull(){
       heroRepository.remove(null);

    }
    @Test (expected = NullPointerException.class)
    public void test_Remove_ShouldThrowWhenNameIsEmpty(){
        heroRepository.remove("     ");
    }

    @Test
    public void test_Remove_ShouldRemoveValidHero(){
        heroRepository.create(hero);
        boolean isRemoved = heroRepository.remove("Gosho");
        Assert.assertEquals(0, heroRepository.getCount());
        Assert.assertTrue(isRemoved);
    }

    @Test
    public void test_Remove_ShouldReturnFalseIfTryToRemoveInvalidName(){
        heroRepository.create(hero);
        boolean isRemoved = heroRepository.remove("Pesho");
        Assert.assertFalse(isRemoved);
    }

    @Test
    public void test_getHeroWithHighestLevel_ShouldReturnValidHero(){
        heroRepository.create(hero);
        heroRepository.create(hero2);
        heroRepository.create(hero3);

        Hero maxLevelHero = heroRepository.getHeroWithHighestLevel();
        Assert.assertEquals("Toshko", maxLevelHero.getName());
    }

    @Test
    public void test_getHeroWithHighestLevel_ShouldReturnNullIsRepoIsEmpty(){
        Assert.assertNull(heroRepository.getHeroWithHighestLevel());
    }

    @Test
    public void test_getHero_ShouldReturnNullIfProvideUnexistName(){
        heroRepository.create(hero);
        Assert.assertNull(heroRepository.getHero("Pesho"));
    }

    @Test
    public void test_getHero_ShouldReturnValidHero(){
        heroRepository.create(hero);
        Assert.assertEquals("Gosho", heroRepository.getHero("Gosho").getName());
    }

    @Test (expected = UnsupportedOperationException.class)
    public void test_getHeroes_ShouldReturnUnmodifiableCollection(){
        heroRepository.getHeroes().add(hero);
    }
}
