package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.model.entity.ModelEntity;
import bg.softuni.mobilelele.model.view.ModelSummeryView;

import java.util.List;

public interface ModelService {
    void populateModels();

    ModelEntity findById(long id);

    List<ModelSummeryView> getAll();


    boolean existsByModel(String model);

    void save(ModelEntity model);

    ModelEntity findByName(String name);
}
