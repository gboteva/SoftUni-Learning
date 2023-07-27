package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.model.view.OfferDetailsView;
import bg.softuni.mobilelele.model.view.OfferSummeryView;
import bg.softuni.mobilelele.service.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class OfferController {
    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("offers/all")
    public String allOffers(Model model){
        model.addAttribute("offers", offerService.getOffers());
        return "offers";
    }

    @GetMapping("/offers/details/{id}")
    private String details(@PathVariable Long id, Model model){
       OfferDetailsView offerDetailsView = offerService.getOfferById(id);
       model.addAttribute("offer", offerDetailsView);

        return "details";
    }


    @GetMapping("offers/add")
    public String addOffer(Model model){
        return "offer-add";
    }



}
