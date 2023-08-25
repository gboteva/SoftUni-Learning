package bg.softuni.mobilelele.repository;

import bg.softuni.mobilelele.model.entity.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<ModelEntity, Long> {
    boolean existsByName(String model);

    ModelEntity findByName(String name);
}
