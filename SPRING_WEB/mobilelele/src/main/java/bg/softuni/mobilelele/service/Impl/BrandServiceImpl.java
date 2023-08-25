package bg.softuni.mobilelele.service.Impl;

import bg.softuni.mobilelele.model.entity.BrandEntity;
import bg.softuni.mobilelele.model.view.BrandSummeryView;
import bg.softuni.mobilelele.repository.BrandRepository;
import bg.softuni.mobilelele.service.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;

    public BrandServiceImpl(BrandRepository brandRepository, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void populateBrands() {
        if (brandRepository.count() == 0) {

            BrandEntity bmw = new BrandEntity();
            bmw.setName("BMW");
            bmw.setCreated(LocalDateTime.now());

            brandRepository.save(bmw);

            BrandEntity toyota = new BrandEntity();
            toyota.setName("Toyota");
            toyota.setCreated(LocalDateTime.now());

            brandRepository.save(toyota);

            BrandEntity yamaha = new BrandEntity();
            yamaha.setName("Yamaha");
            yamaha.setCreated(LocalDateTime.now());

            brandRepository.save(yamaha);
        }
    }

    @Override
    public BrandEntity findById(Long id) {
        return brandRepository.findById(id).orElse(null);
    }

    @Override
    public List<BrandSummeryView> getAll() {
        List<BrandEntity> brands = brandRepository.findAll();

        return brands.stream().map(entity -> modelMapper.map(entity, BrandSummeryView.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByBrand(String brand) {
        return brandRepository.existsByName(brand);
    }

    @Override
    public void save(BrandEntity brand) {
        brandRepository.save(brand);
    }

    @Override
    public BrandEntity findByName(String name) {

        return brandRepository.findByName(name);
    }


}
