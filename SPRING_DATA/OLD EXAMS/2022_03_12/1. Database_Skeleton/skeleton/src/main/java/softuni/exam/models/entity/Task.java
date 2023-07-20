package softuni.exam.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

@Entity
@Table(name = "tasks")
public class Task extends BaseEntity{
    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Date date;

    @ManyToOne()
    private Part parts;

    @ManyToOne()
    private Mechanic mechanic;

    @ManyToOne()
    private Car cars;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Part getPart() {
        return parts;
    }

    public void setPart(Part part) {
        this.parts = part;
    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    public void setMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
    }

    public Car getCar() {
        return cars;
    }

    public void setCar(Car car) {
        this.cars = car;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        DecimalFormat engineFormatter = new DecimalFormat("#####################################.0#");
        DecimalFormat priceFormatter = new DecimalFormat("#####################################.00");
        engineFormatter.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        priceFormatter.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ENGLISH));


        sb.append(String.format("Car %s %s with %dkm\n" +
                "-Mechanic: %s %s - task №%d:\n" +
                " --Engine: %s\n" +
                "---Price: %s$\n",
                getCar().getCarMake(), getCar().getCarModel(), getCar().getKilometers(),
                getMechanic().getFirstName(), getMechanic().getLastName(),
                getId(),
                engineFormatter.format(getCar().getEngine()),
                priceFormatter.format(getPrice())
                ));

        return sb.toString();
    }
}
