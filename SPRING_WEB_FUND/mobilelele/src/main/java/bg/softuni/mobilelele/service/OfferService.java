package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.view.OfferSummeryView;

import java.util.List;

public interface OfferService {
    void populateOffer();

    List<OfferSummeryView> getOffers();
}
