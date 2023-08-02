package bg.softuni.mobilelele.service.Impl;

import bg.softuni.mobilelele.model.binding.UpdateOfferBindingModel;
import bg.softuni.mobilelele.model.entity.BrandEntity;
import bg.softuni.mobilelele.model.entity.ModelEntity;
import bg.softuni.mobilelele.model.entity.OfferEntity;
import bg.softuni.mobilelele.model.entity.enums.Category;
import bg.softuni.mobilelele.model.entity.enums.EngineEnum;
import bg.softuni.mobilelele.model.entity.enums.TransmissionEnum;
import bg.softuni.mobilelele.model.service.AddOfferServiceModel;
import bg.softuni.mobilelele.model.service.UpdateOfferServiceModel;
import bg.softuni.mobilelele.model.view.OfferDetailsView;
import bg.softuni.mobilelele.repository.OfferRepository;
import bg.softuni.mobilelele.service.BrandService;
import bg.softuni.mobilelele.service.ModelService;
import bg.softuni.mobilelele.service.OfferService;
import bg.softuni.mobilelele.model.view.OfferSummeryView;
import bg.softuni.mobilelele.service.UserService;
import bg.softuni.mobilelele.web.exceptions.ObjectAlreadyExistsException;
import bg.softuni.mobilelele.web.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;
    private final ModelService modelService;
    private final UserService userService;
    private final BrandService brandService;

    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper modelMapper, ModelService modelService, UserService userService, BrandService brandService) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.modelService = modelService;
        this.userService = userService;
        this.brandService = brandService;
    }

    @Override
    public void populateOffer() {
        if (offerRepository.count() > 0) {
            return;
        }

        OfferEntity offer = new OfferEntity();
        offer.setYear(2022);
        offer.setCreated(LocalDateTime.now());
        offer.setDescription("Some new top BMW car");
        offer.setEngine(EngineEnum.ELECTRIC);
        offer.setMileage(200);
        offer.setImageUrl("https://www.motortrend.com/uploads/sites/5/2021/08/2022-BMW-i4-2.jpg?fit=around%7C875:492");
        offer.setModel(modelService.findById(1L));
        offer.setPrice(BigDecimal.valueOf(200000L));
        offer.setTransmission(TransmissionEnum.AUTOMATIC);
        offer.setSeller(userService.findById(2L));

        OfferEntity offer2 = new OfferEntity();
        offer2.setYear(2020);
        offer2.setCreated(LocalDateTime.now());
        offer2.setDescription("Some new top Toyota car");
        offer2.setEngine(EngineEnum.HYBRID);
        offer2.setMileage(100);
        offer2.setImageUrl("https://www.completecar.ie/img/testdrives/9957_large.jpg");
        offer2.setModel(modelService.findById(2L));
        offer2.setPrice(BigDecimal.valueOf(150000L));
        offer2.setTransmission(TransmissionEnum.AUTOMATIC);
        offer2.setSeller(userService.findById(3L));

        OfferEntity offer3 = new OfferEntity();
        offer3.setYear(2021);
        offer3.setCreated(LocalDateTime.now());
        offer3.setDescription("Yamaha MZ 15");
        offer3.setEngine(EngineEnum.GASOLINE);
        offer3.setMileage(20);
        offer3.setImageUrl("https://imgd.aeplcdn.com/1280x720/n/cw/ec/129515/yamaha-fz-right-side-view0.jpeg?isig=0");
        offer3.setModel(modelService.findById(3L));
        offer3.setPrice(BigDecimal.valueOf(100000L));
        offer3.setTransmission(TransmissionEnum.MANUAL);
        offer3.setSeller(userService.findById(3L));


        offerRepository.saveAll(List.of(offer, offer2, offer3));
    }

    @Override
    public List<OfferSummeryView> getOffers() {
        return offerRepository.findAll().stream()
                .map(this::map).collect(Collectors.toList());
    }

    @Override
    public OfferDetailsView getOfferById(Long id) {
        Optional<OfferEntity> offerEntity = offerRepository.findById(id);

        OfferDetailsView mapped = modelMapper.map(offerEntity, OfferDetailsView.class);

        mapped.setSeller(offerEntity.get().getSeller());

        return mapped;
    }

    @Override
    public void deleteOffer(Long id) {
        offerRepository.deleteById(id);
    }

    @Override
    public UpdateOfferBindingModel findById(Long id) {

        UpdateOfferBindingModel offer = modelMapper.map(offerRepository.findById(id), UpdateOfferBindingModel.class);

        return offer;
    }

    @Override
    public void updateOffer(UpdateOfferServiceModel updateServiceModel) {
       OfferEntity offerEntity = offerRepository.findById(updateServiceModel.getId())
               .orElseThrow(() -> new ObjectNotFoundException("Offer with id " + updateServiceModel.getId() + " not found!"));

        offerEntity.setPrice(updateServiceModel.getPrice());
        offerEntity.setMileage(updateServiceModel.getMileage());
        offerEntity.setYear(updateServiceModel.getYear());
        offerEntity.setDescription(updateServiceModel.getDescription());
        offerEntity.setEngine(updateServiceModel.getEngine());
        offerEntity.setTransmission(updateServiceModel.getTransmission());


        offerRepository.save(offerEntity);
    }

    @Override
    public void addOffer(AddOfferServiceModel addOfferModel) {
        if (!brandService.existsByBrand(addOfferModel.getBrand())){
            BrandEntity brand = new BrandEntity();
            brand.setName(addOfferModel.getBrand());
            brand.setCreated(LocalDateTime.now());

            brandService.save(brand);
        }

        if (!modelService.existsByModel(addOfferModel.getModel())){
            ModelEntity model = new ModelEntity();
            model.setName(addOfferModel.getModel());
            model.setBrand(brandService.findByName(addOfferModel.getBrand()));
            model.setCategory(addOfferModel.getCategory());
            model.setImageUrl(addOfferModel.getImageUrl());
            modelService.save(model);
        }


        OfferEntity offer = modelMapper.map(addOfferModel, OfferEntity.class);

        offer.setModel(modelService.findByName(addOfferModel.getModel()));

        offer.setSeller(userService.findById(4));

        offer.setCreated(LocalDateTime.now());

        offerRepository.save(offer);
    }

    private OfferSummeryView map(OfferEntity offer) {
        return modelMapper.map(offer, OfferSummeryView.class);
    }
}
