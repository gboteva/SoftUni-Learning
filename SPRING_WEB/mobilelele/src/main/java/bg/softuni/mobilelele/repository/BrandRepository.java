package bg.softuni.mobilelele.repository;

import bg.softuni.mobilelele.model.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {
    boolean existsByName(String brand);

    BrandEntity findByName(String name);
}
