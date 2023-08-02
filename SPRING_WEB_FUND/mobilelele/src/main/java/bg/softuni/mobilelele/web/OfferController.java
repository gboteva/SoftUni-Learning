package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.model.binding.AddOfferBindingModel;
import bg.softuni.mobilelele.model.binding.UpdateOfferBindingModel;
import bg.softuni.mobilelele.model.entity.enums.Category;
import bg.softuni.mobilelele.model.entity.enums.EngineEnum;
import bg.softuni.mobilelele.model.entity.enums.TransmissionEnum;
import bg.softuni.mobilelele.model.service.AddOfferServiceModel;
import bg.softuni.mobilelele.model.service.UpdateOfferServiceModel;
import bg.softuni.mobilelele.model.view.OfferDetailsView;
import bg.softuni.mobilelele.service.ModelService;
import bg.softuni.mobilelele.service.OfferService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;


@Controller
public class OfferController {
    private final OfferService offerService;
    private final ModelService modelService;
    private final ModelMapper modelMapper;

    public OfferController(OfferService offerService, ModelService modelService, ModelMapper modelMapper) {
        this.offerService = offerService;
        this.modelService = modelService;
        this.modelMapper = modelMapper;
    }

    //Get
    @GetMapping("offers/all")
    public String allOffers(Model model) {
        model.addAttribute("offers", offerService.getOffers());
        return "offers";
    }

    @GetMapping("/offers/details/{id}")
    private String details(@PathVariable Long id, Model model) {
        OfferDetailsView offerDetailsView = offerService.getOfferById(id);

        model.addAttribute("offer", offerDetailsView);

        return "details";
    }

    @GetMapping("offers/add")
    public String addOfferView(Model model) {
        model.addAttribute("engineType", Arrays.stream(EngineEnum.values()).toList());
        model.addAttribute("transmissions", Arrays.stream(TransmissionEnum.values()).toList());
        model.addAttribute("categories", Category.values());

        return "offer-add";
    }

    //Post
    @PostMapping("offers/add")
    public String addOffer(@Valid AddOfferBindingModel addModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes
                           ) {

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("model", addModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.model", bindingResult);
            return "redirect: offer/add/errors";
        }

        AddOfferServiceModel addOfferModel = modelMapper.map(addModel, AddOfferServiceModel.class);

        offerService.addOffer(addOfferModel);

        return "redirect:/offers/all";
    }

    @GetMapping("/offers/add/errors")
    public String addOfferErrors(Model model) {
        model.addAttribute("engineType", Arrays.stream(EngineEnum.values()).toList());
        model.addAttribute("transmissions", Arrays.stream(TransmissionEnum.values()).toList());

        return "/offer-add";
    }

    //Delete
    @DeleteMapping("offers/{id}")
    public String deleteOffer(@PathVariable Long id) {
        offerService.deleteOffer(id);

        return "redirect:/offers/all";
    }

    //Update
    @GetMapping("/offers/{id}/edit")
    public String updateOffer(@PathVariable Long id, Model model) {
        UpdateOfferBindingModel updateOfferBindingModel = offerService.findById(id);

        model.addAttribute("offer", updateOfferBindingModel);
        model.addAttribute("engineTypes", EngineEnum.values());
        model.addAttribute("transmissions", TransmissionEnum.values());

        return "/update";
    }

    @PatchMapping(value = "offers/{id}/edit", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String updateOfferEntity(@PathVariable Long id,
                                    @Valid UpdateOfferBindingModel offerModel,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes) {

        //validation
        if (bindingResult.hasErrors()) {
            //name must be same as like variable in update getMapping
            redirectAttributes.addFlashAttribute("offer", offerModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offer", bindingResult);

            return "redirect:/offers/" + id + "/edit/errors";

        }

        //update
        UpdateOfferServiceModel mapped = modelMapper.map(offerModel, UpdateOfferServiceModel.class);

        offerService.updateOffer(mapped);

        return "redirect:/offers/details/{id}";
    }


    @GetMapping("/offers/{id}/edit/errors")
    public String updateOfferErrors(@PathVariable Long id, Model model) {
        model.addAttribute("engineTypes", EngineEnum.values());
        model.addAttribute("transmissions", TransmissionEnum.values());
        return "update";
    }


}
