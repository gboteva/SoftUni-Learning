package exam.service.impl;

import exam.model.dto.ShopSeedRootDto;
import exam.model.entity.Shop;
import exam.repository.ShopRepository;
import exam.service.ShopService;
import exam.service.TownService;
import exam.util.ValidationUtil;
import exam.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopServiceImpl implements ShopService {
    public static final String SHOP_INPUT_FILE_PATH = "src/main/resources/files/xml/shops.xml";
    private final ShopRepository shopRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;

    private final TownService townService;

    public ShopServiceImpl(ShopRepository shopRepository, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser, TownService townService) {
        this.shopRepository = shopRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
        this.townService = townService;
    }

    @Override
    public boolean areImported() {
        return shopRepository.count() > 0;
    }

    @Override
    public String readShopsFileContent() throws IOException {
        return Files.readString(Path.of(SHOP_INPUT_FILE_PATH));
    }

    @Override
    public String importShops() throws JAXBException, FileNotFoundException {
        ShopSeedRootDto shopSeedRootDto = xmlParser.fromFile(SHOP_INPUT_FILE_PATH, ShopSeedRootDto.class);

        StringBuilder sb = new StringBuilder();

        shopSeedRootDto.getShops()
                .stream().filter(dto -> {
                    boolean isValid = validationUtil.isValid(dto)
                            && !isExistShopByName(dto.getName())
                            && isExistTownByName(dto.getTown().getName());

                    if (isValid) {
                        sb.append(String.format("Successfully imported Shop %s - %s",
                                dto.getName(), dto.getIncome()));
                    } else {
                        sb.append("Invalid shop");
                    }

                    sb.append(System.lineSeparator());

                    return isValid;
                })
                .map(dto -> {
                    Shop shop = modelMapper.map(dto, Shop.class);
                    shop.setTown(townService.getTownByName(dto.getTown().getName()));
                    return shop;
                }).forEach(shopRepository::save);

        return sb.toString().trim();
    }

    @Override
    public Shop getShopByName(String name) {
        return shopRepository.findByName(name);

    }

    private boolean isExistTownByName(String townName) {
        return townService.isExistTownByName(townName);
    }

    private boolean isExistShopByName(String shopName) {
        return shopRepository.existsByName(shopName);
    }
}
