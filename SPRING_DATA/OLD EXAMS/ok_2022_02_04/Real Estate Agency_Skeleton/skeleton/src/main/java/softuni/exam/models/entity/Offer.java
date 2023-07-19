package softuni.exam.models.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "offers")
public class Offer extends BaseEntity {
    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "published_od", nullable = false)
    private LocalDate publishedOn;
    @ManyToOne
    private Apartment apartment;
    @ManyToOne
    private Agent agent;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getPublishedOn() {
        return publishedOn;
    }

    public void setPublishedOn(LocalDate publishedOn) {
        this.publishedOn = publishedOn;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    @Override
    public String toString() {
        return String.format("Agent %s %s with offer №%d:\n" +
                "\t\t-Apartment area: %.2f\n" +
                "\t\t--Town: %s\n" +
                "\t\t---Price: %.2f$\n", agent.getFirstName(), agent.getLastName(),
                this.getId(), apartment.getArea(), apartment.getTown().getTownName(),
                price);
    }
}
