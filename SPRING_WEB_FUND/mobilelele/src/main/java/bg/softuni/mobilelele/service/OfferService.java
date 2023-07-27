package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.model.view.OfferDetailsView;
import bg.softuni.mobilelele.model.view.OfferSummeryView;

import java.util.List;

public interface OfferService {
    void populateOffer();

    List<OfferSummeryView> getOffers();

    OfferDetailsView getOfferById(Long id);
}
