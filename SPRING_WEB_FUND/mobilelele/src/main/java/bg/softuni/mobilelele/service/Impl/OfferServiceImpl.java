package bg.softuni.mobilelele.service.Impl;

import bg.softuni.mobilelele.model.entity.OfferEntity;
import bg.softuni.mobilelele.repository.OfferRepository;
import bg.softuni.mobilelele.service.OfferService;
import bg.softuni.mobilelele.view.OfferSummeryView;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;

    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public void populateOffer() {

    }

    @Override
    public List<OfferSummeryView> getOffers() {
        return offerRepository.findAll().stream()
                .map(this::map).collect(Collectors.toList());
    }

    private OfferSummeryView map(OfferEntity offer){
        OfferSummeryView offerView = new OfferSummeryView();
        // TODO: 24.7.2023 г.
        return offerView;
    }
}
