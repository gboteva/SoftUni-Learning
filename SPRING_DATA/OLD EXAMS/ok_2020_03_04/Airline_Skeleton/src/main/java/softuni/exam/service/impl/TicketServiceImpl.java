package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.TicketRootSeedDto;
import softuni.exam.models.entities.Ticket;
import softuni.exam.repository.TicketRepository;
import softuni.exam.service.PassengerService;
import softuni.exam.service.PlaneService;
import softuni.exam.service.TicketService;
import softuni.exam.service.TownService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {
    public static final String TICKET_INPUT_PATH = "src/main/resources/files/xml/tickets.xml";

    private final TicketRepository ticketRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;

    private final TownService townService;
    private final PlaneService planeService;
    private final PassengerService passengerService;


    public TicketServiceImpl(TicketRepository ticketRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validationUtil, TownService townService, PlaneService planeService, PassengerService passengerService) {
        this.ticketRepository = ticketRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.townService = townService;
        this.planeService = planeService;
        this.passengerService = passengerService;
    }

    @Override
    public boolean areImported() {
        return ticketRepository.count() > 0;
    }

    @Override
    public String readTicketsFileContent() throws IOException {
        return Files.readString(Path.of(TICKET_INPUT_PATH));
    }

    @Override
    public String importTickets() throws JAXBException, FileNotFoundException {

        TicketRootSeedDto ticketRootSeedDto = xmlParser.fromFile(TICKET_INPUT_PATH, TicketRootSeedDto.class);
        StringBuilder sb = new StringBuilder();

        ticketRootSeedDto.getTickets().stream()
                .filter(dto -> {
                    boolean isValid = validationUtil.isValid(dto)
                            && !isExistByNumber(dto.getSerialNumber())
                            && townService.isExistByName(dto.getFromTown().getName())
                            && townService.isExistByName(dto.getToTown().getName())
                            && passengerService.isExistByEmail(dto.getPassenger().getEmail())
                            && planeService.existByRegisterNumber(dto.getPlane().getRegisterNumber());

                    if (isValid) {
                        sb.append(String.format("Successfully imported Ticket %s - %s",
                                dto.getFromTown().getName(),
                                dto.getToTown().getName()));
                    } else {
                        sb.append("Invalid Ticket");
                    }

                    sb.append(System.lineSeparator());


                    return isValid;
                })
                .map(dto -> {
                    Ticket ticket = modelMapper.map(dto, Ticket.class);
                    ticket.setFromTown(townService.getTownByName(dto.getFromTown().getName()));
                    ticket.setToTown(townService.getTownByName(dto.getToTown().getName()));
                    ticket.setPassenger(passengerService.getByEmail(dto.getPassenger().getEmail()));
                    ticket.setPlane(planeService.getByNumber(dto.getPlane().getRegisterNumber()));

                    return ticket;
                }).forEach(ticketRepository::save);

        return sb.toString().trim();
    }

    private boolean isExistByNumber(String serialNumber) {
        return ticketRepository.existsBySerialNumber(serialNumber);
    }
}
