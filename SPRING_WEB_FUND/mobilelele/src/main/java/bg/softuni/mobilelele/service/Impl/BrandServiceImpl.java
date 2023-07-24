package bg.softuni.mobilelele.service.Impl;

import bg.softuni.mobilelele.model.entity.BrandEntity;
import bg.softuni.mobilelele.repository.BrandRepository;
import bg.softuni.mobilelele.service.BrandService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public void populateBrands() {
        if (brandRepository.count() == 0){
            BrandEntity seat = new BrandEntity();
            seat.setName("Seat");
            seat.setCreated(LocalDateTime.now());

            brandRepository.save(seat);
        }
    }
}
