package softuni.exam.models.dto;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@XmlAccessorType(XmlAccessType.FIELD)
public class TicketSeedDto {
    @XmlElement(name = "serial-number")
    @Size(min = 2)
    private String serialNumber;
    @Positive
    private BigDecimal price;
    @XmlElement(name = "take-off")
    private String takeoff;

    @XmlElement(name = "from-town")
    private FromAndTownSeedDto fromTown;

    @XmlElement(name = "to-town")
    private FromAndTownSeedDto toTown;

    @XmlElement(name = "passenger")
    private PassengerTicketSeedDto passenger;

    @XmlElement(name = "plane")
    private PlaneTicketSeedDto plane;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTakeoff() {
        return takeoff;
    }

    public void setTakeoff(String takeoff) {
        this.takeoff = takeoff;
    }

    public FromAndTownSeedDto getFromTown() {
        return fromTown;
    }

    public void setFromTown(FromAndTownSeedDto fromTown) {
        this.fromTown = fromTown;
    }

    public FromAndTownSeedDto getToTown() {
        return toTown;
    }

    public void setToTown(FromAndTownSeedDto toTown) {
        this.toTown = toTown;
    }

    public PassengerTicketSeedDto getPassenger() {
        return passenger;
    }

    public void setPassenger(PassengerTicketSeedDto passenger) {
        this.passenger = passenger;
    }

    public PlaneTicketSeedDto getPlane() {
        return plane;
    }

    public void setPlane(PlaneTicketSeedDto plane) {
        this.plane = plane;
    }
}
