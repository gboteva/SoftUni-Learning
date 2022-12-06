package football;

import com.sun.jdi.InvalidLineNumberException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FootballTeamTests {
    Footballer footballer;
    FootballTeam footballTeamWithOnePosition;

    @Before
    public void setup(){
        footballer = new Footballer("Gosho");
        footballTeamWithOnePosition = new FootballTeam("Levski", 1);
    }

    @Test (expected = NullPointerException.class)
    public void test_setName_ShouldThrowIfInvalidNameIsGiven(){
        footballTeamWithOnePosition = new FootballTeam("   ", 5);
    }
    @Test (expected = NullPointerException.class)
    public void test_setName_ShouldThrowNameIsNull(){
        footballTeamWithOnePosition = new FootballTeam(null, 5);
    }
    @Test
    public void setName_should_setValidName(){
        String actual = footballTeamWithOnePosition.getName();
        String expected = "Levski";
        Assert.assertEquals(expected, actual);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSetVacantPosition_shouldThrowWhenCountOfPositionsIsLessThenZero(){
        footballTeamWithOnePosition = new FootballTeam("CKSA", -1);
    }

    @Test
    public void setVacantPositions_should_setCorrectValie(){
        Assert.assertEquals(1, footballTeamWithOnePosition.getVacantPositions());
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_addFootballer_shouldThrowWhenVacantPositionsIsFull(){
        footballTeamWithOnePosition.addFootballer(footballer);
        Footballer newPlayer = new Footballer("Pesho");
        footballTeamWithOnePosition.addFootballer(newPlayer);
    }

    @Test
    public void testAddFootballer_ShouldAddPlayerWhenIsEnoughVacantPositions(){
        footballTeamWithOnePosition.addFootballer(footballer);
        Assert.assertEquals(1, footballTeamWithOnePosition.getCount());
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_removeFootballer_shouldThrowWhenFootballerIsNull(){
        footballTeamWithOnePosition.addFootballer(footballer);
        footballTeamWithOnePosition.removeFootballer("Pesho");
    }

    @Test
    public void test_removeFootballer_shouldRemoveExistPlayer(){
        footballTeamWithOnePosition = new FootballTeam("CSKA", 5);
        footballTeamWithOnePosition.addFootballer(footballer);
        footballTeamWithOnePosition.addFootballer(new Footballer("Pesho"));
        footballTeamWithOnePosition.removeFootballer("Gosho");
        Assert.assertEquals(1, footballTeamWithOnePosition.getCount());
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_footballerForSale_shouldThrowWhenFootballerIsNull(){
        footballTeamWithOnePosition.addFootballer(footballer);
        footballTeamWithOnePosition.footballerForSale("Pesho");
    }

    @Test
    public void test_footballerForSale_shouldSetFalseToActiveOnExistPlayer(){
        footballTeamWithOnePosition.addFootballer(footballer);
        footballTeamWithOnePosition.footballerForSale("Gosho");
        boolean actual = footballer.isActive();

        Assert.assertFalse(actual);
    }

    @Test
    public void test_getStatistics_shouldReturnCorrectValue(){
        FootballTeam team = new FootballTeam("CSKA", 3);
        Footballer first = new Footballer("Ivan");
        Footballer second = new Footballer("Pesho");
        Footballer third = new Footballer("Gosho");
        team.addFootballer(first);
        team.addFootballer(second);
        team.addFootballer(third);
        String expected = String.format("The footballer Ivan, Pesho, Gosho is in the team CSKA.");
        Assert.assertEquals(expected, team.getStatistics());

    }



}
