package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.service.BrandService;
import bg.softuni.mobilelele.service.ModelService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class ModelAndBrandController {
    private final BrandService brandService;
    private final ModelService modelService;

    public ModelAndBrandController(BrandService brandService, ModelService modelService) {
        this.brandService = brandService;
        this.modelService = modelService;
    }

    @ModelAttribute("servletPath")
    String getRequestServletPath(HttpServletRequest request) {
        return request.getServletPath();
    }

    @GetMapping("brands/all")
    public String allBrands(Model model){
        model.addAttribute("models", modelService.getAll());
        model.addAttribute("brands", brandService.getAll());

        return "brands";
    }
}
