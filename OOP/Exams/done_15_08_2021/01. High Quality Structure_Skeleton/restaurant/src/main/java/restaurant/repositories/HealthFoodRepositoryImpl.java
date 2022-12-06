package restaurant.repositories;

import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.repositories.interfaces.HealthFoodRepository;

public class HealthFoodRepositoryImpl extends BaseRepository<HealthyFood> implements HealthFoodRepository<HealthyFood> {


    @Override
    public HealthyFood foodByName(String name) {
        return super.getAllEntities().stream()
                .filter(f->f.getName().equals(name))
                .findFirst()
                .orElse(null);
    }


}
