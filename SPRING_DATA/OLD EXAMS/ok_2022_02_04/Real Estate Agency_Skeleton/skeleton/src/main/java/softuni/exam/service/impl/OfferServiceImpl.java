package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.OfferSeedRootDto;
import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.Offer;
import softuni.exam.models.entity.enums.ApartmentType;
import softuni.exam.repository.OfferRepository;
import softuni.exam.service.AgentService;
import softuni.exam.service.ApartmentService;
import softuni.exam.service.OfferService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {
    public static final String OFFERS_INPUT_PATH = "src/main/resources/files/xml/offers.xml";
    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;
    private final AgentService agentService;
    private final ApartmentService apartmentService;

    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser, AgentService agentService, ApartmentService apartmentService) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
        this.agentService = agentService;
        this.apartmentService = apartmentService;
    }

    @Override
    public boolean areImported() {
        return this.offerRepository.count() > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return Files.readString(Path.of(OFFERS_INPUT_PATH));
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        OfferSeedRootDto offerSeedRootDto = xmlParser.fromFile(OFFERS_INPUT_PATH, OfferSeedRootDto.class);
        StringBuilder sb = new StringBuilder();

        offerSeedRootDto.getOffers().stream().filter(seedDto -> {
            boolean isValid = validationUtil.isValid(seedDto)
                    && isExistAgent(seedDto.getAgent().getName());

            if (isValid) {
                sb.append(String.format("Successfully imported offer %.2f", seedDto.getPrice()));
            } else {
                sb.append("Invalid offer");
            }
            sb.append(System.lineSeparator());

            return isValid;
        }).map(dto -> {
            Offer offer = modelMapper.map(dto, Offer.class);

            Apartment apartment = apartmentService.findById(dto.getApartment().getId());
            Agent agent = agentService.findByName(dto.getAgent().getName());

            offer.setAgent(agent);
            offer.setApartment(apartment);
            return offer;
        }).forEach(offerRepository::save);


        return sb.toString().trim();
    }

    private boolean isExistAgent(String agentName) {
        return agentService.isExistAgentByName(agentName);
    }

    @Override
    public String exportOffers() {
        List<Offer> exportOffers = offerRepository.findAllByApartmentType(ApartmentType.three_rooms);

        StringBuilder sb = new StringBuilder();

        exportOffers.forEach(sb::append);

        return sb.toString().trim();
    }
}
