package bg.softuni.mobilelele.init;

import bg.softuni.mobilelele.service.BrandService;
import bg.softuni.mobilelele.service.UserRoleService;
import bg.softuni.mobilelele.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final BrandService brandService;
    private final UserService userService;

    private final UserRoleService userRoleService;


    public DataInitializer(BrandService brandService, UserService userService, UserRoleService userRoleService) {
        this.brandService = brandService;
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    @Override
    public void run(String... args) throws Exception {
       brandService.populateBrands();
       userRoleService.populateRoles();
       userService.populateUsers();
    }
}
