package softuni.exam.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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
}
