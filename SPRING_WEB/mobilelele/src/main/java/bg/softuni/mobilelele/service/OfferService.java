package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.model.binding.UpdateOfferBindingModel;
import bg.softuni.mobilelele.model.service.AddOfferServiceModel;
import bg.softuni.mobilelele.model.service.UpdateOfferServiceModel;
import bg.softuni.mobilelele.model.view.OfferDetailsView;
import bg.softuni.mobilelele.model.view.OfferSummeryView;

import java.util.List;

public interface OfferService {
    void populateOffer();

    List<OfferSummeryView> getOffers();

    OfferDetailsView getOfferById(Long id);

    void deleteOffer(Long id);

    UpdateOfferBindingModel findById(Long id);

    void updateOffer(UpdateOfferServiceModel updateServiceModel);

    void addOffer(AddOfferServiceModel addOfferModel);
}
