package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.model.entity.BrandEntity;
import bg.softuni.mobilelele.model.view.BrandSummeryView;

import java.util.List;

public interface BrandService {
    void  populateBrands();

    BrandEntity findById(Long id);

    List<BrandSummeryView> getAll();

    boolean existsByBrand(String brand);

    void save(BrandEntity brand);

    BrandEntity findByName(String name);
}
