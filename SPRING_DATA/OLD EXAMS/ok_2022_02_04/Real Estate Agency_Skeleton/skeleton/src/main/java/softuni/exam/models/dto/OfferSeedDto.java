package softuni.exam.models.dto;

import javax.lang.model.element.Name;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "offer")
@XmlAccessorType(XmlAccessType.FIELD)
public class OfferSeedDto {
    @XmlElement(name = "price")
    @Positive
    private BigDecimal price;
    @XmlElement(name = "agent")
    private AgentWithNameDto agent;
    @XmlElement(name = "apartment")
    private ApartmentWithIdDTO apartment;
    @XmlElement(name = "publishedOn")
    private String publishedOn;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public AgentWithNameDto getAgent() {
        return agent;
    }

    public void setAgent(AgentWithNameDto agent) {
        this.agent = agent;
    }

    public ApartmentWithIdDTO getApartment() {
        return apartment;
    }

    public void setApartment(ApartmentWithIdDTO apartment) {
        this.apartment = apartment;
    }

    public String getPublishedOn() {
        return publishedOn;
    }

    public void setPublishedOn(String publishedOn) {
        this.publishedOn = publishedOn;
    }
}
