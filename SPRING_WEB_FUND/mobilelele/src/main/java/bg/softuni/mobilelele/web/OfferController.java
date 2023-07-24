package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.service.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
