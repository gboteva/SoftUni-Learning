package restaurant.core;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import restaurant.core.interfaces.Controller;
import restaurant.entities.drinks.Fresh;
import restaurant.entities.drinks.Smoothie;
import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.healthyFoods.Food;
import restaurant.entities.healthyFoods.Salad;
import restaurant.entities.healthyFoods.VeganBiscuits;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.tables.InGarden;
import restaurant.entities.tables.interfaces.Table;
import restaurant.repositories.BeverageRepositoryImpl;
import restaurant.repositories.HealthFoodRepositoryImpl;
import restaurant.repositories.TableRepositoryImpl;
import restaurant.repositories.interfaces.BeverageRepository;
import restaurant.repositories.interfaces.HealthFoodRepository;
import restaurant.repositories.interfaces.TableRepository;

class EngineImplTest {
    private HealthyFood food1;
    private HealthyFood food2;
    private Beverages beverages1;
    private Beverages beverages2;
    private Table table;
    private HealthFoodRepositoryImpl healthFoodRepository;
    private BeverageRepository<Beverages> beverageRepository;
    private TableRepository<Table> tableRepository;
    private Controller controller;

    @Before
    public void setup(){
        healthFoodRepository = new HealthFoodRepositoryImpl();
        beverageRepository = new BeverageRepositoryImpl();
        tableRepository = new TableRepositoryImpl();
        controller = new ControllerImpl(healthFoodRepository, beverageRepository, tableRepository);

        food1 = new Salad("Galka", 5);
        food2 = new VeganBiscuits("Krasi", 10);

        beverages1 = new Smoothie("Smoothy", 3, "Banana");
        beverages2 = new Fresh("Fresh", 2, "Orange");

        table = new InGarden(1, 10);
    }
    @Test
    public void testAddFood(){
        controller.addTable("InGarden", 1, 10);
    }

}