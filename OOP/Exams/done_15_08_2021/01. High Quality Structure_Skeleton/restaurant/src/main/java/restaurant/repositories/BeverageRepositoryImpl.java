package restaurant.repositories;

import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.repositories.interfaces.BeverageRepository;


public class BeverageRepositoryImpl extends BaseRepository<Beverages> implements BeverageRepository<Beverages> {

    @Override
    public Beverages beverageByName(String drinkName, String drinkBrand) {
        //return beverage with name????
        return super.getAllEntities().stream()
                .filter(b->b.getName().equals(drinkName) && b.getBrand().equals(drinkBrand))
                .findFirst()
                .orElse(null);
    }


}
