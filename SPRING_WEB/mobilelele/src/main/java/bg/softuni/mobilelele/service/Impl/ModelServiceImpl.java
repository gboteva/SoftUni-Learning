package bg.softuni.mobilelele.service.Impl;

import bg.softuni.mobilelele.model.entity.ModelEntity;
import bg.softuni.mobilelele.model.entity.enums.Category;
import bg.softuni.mobilelele.model.view.ModelSummeryView;
import bg.softuni.mobilelele.repository.ModelRepository;
import bg.softuni.mobilelele.service.BrandService;
import bg.softuni.mobilelele.service.ModelService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;
    private final BrandService brandService;

    private final ModelMapper modelMapper;

    public ModelServiceImpl(ModelRepository modelRepository, BrandService brandService, ModelMapper modelMapper) {
        this.modelRepository = modelRepository;
        this.brandService = brandService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void populateModels() {
        if (modelRepository.count() > 0) {
            return;
        }


        ModelEntity model2 = new ModelEntity();
        model2.setCategory(Category.Car);
        model2.setBrand(brandService.findById(1L));
        model2.setName("BMW I4");
        model2.setStartYear(2022);
        model2.setEndYear(2120);
        model2.setImageUrl("https://media.ed.edmunds-media.com/bmw/i4/2022/oem/2022_bmw_i4_sedan_edrive40_fq_oem_1_1280.jpg");

        ModelEntity model3 = new ModelEntity();
        model3.setCategory(Category.Car);
        model3.setBrand(brandService.findById(2L));
        model3.setName("Toyota Corola");
        model3.setStartYear(2021);
        model3.setEndYear(2055);
        model3.setImageUrl("https://images.drive.com.au/caradvice/image/private/c_fill,f_auto,g_auto,h_674,q_auto:eco,w_1200/v1/o9gh9ssmhlgmhiiyepm3");

        ModelEntity model4 = new ModelEntity();
        model4.setCategory(Category.Motorcycle);
        model4.setBrand(brandService.findById(3L));
        model4.setName("Yamaha R6");
        model4.setStartYear(2020);
        model4.setEndYear(2035);
        model4.setImageUrl("https://i.ytimg.com/vi/AqUSgyiGCaI/maxresdefault.jpg");

        ModelEntity model5 = new ModelEntity();
        model5.setCategory(Category.Motorcycle);
        model5.setBrand(brandService.findById(3L));
        model5.setName("Yamaha Fazer FZ15");
        model5.setStartYear(2023);
        model5.setEndYear(2070);
        model5.setImageUrl("https://i0.statig.com.br/bancodeimagens/1k/8w/0c/1k8w0cnlqjs5engnonfb46m2c.jpg");

        modelRepository.saveAll(List.of(model2, model3, model4, model5));
    }

    @Override
    public ModelEntity findById(long id) {
        return modelRepository.findById(id).orElse(null);
    }

    @Override
    public List<ModelSummeryView> getAll() {
        List<ModelEntity> allModelEntities = modelRepository.findAll();

        return allModelEntities.stream().map(modelEntity -> modelMapper.map(modelEntity, ModelSummeryView.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByModel(String model) {
       return modelRepository.existsByName(model);
    }

    @Override
    public void save(ModelEntity model) {
        modelRepository.save(model);
    }

    @Override
    public ModelEntity findByName(String name) {

       return modelRepository.findByName(name);
    }
}
