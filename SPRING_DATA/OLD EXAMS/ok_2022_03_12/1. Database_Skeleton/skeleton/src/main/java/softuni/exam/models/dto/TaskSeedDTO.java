package softuni.exam.models.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.Date;

@XmlRootElement(name = "task")
@XmlAccessorType(XmlAccessType.FIELD)
public class TaskSeedDTO {
    @NotBlank
    private String date;

    @Positive
    @NotNull
    private BigDecimal price;

    private TaskCarDTO car;

    private TaskMechanicDto mechanic;

    private TaskPartDto part;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public TaskCarDTO getCar() {
        return car;
    }

    public void setCar(TaskCarDTO car) {
        this.car = car;
    }

    public TaskMechanicDto getMechanic() {
        return mechanic;
    }

    public void setMechanic(TaskMechanicDto mechanic) {
        this.mechanic = mechanic;
    }

    public TaskPartDto getPart() {
        return part;
    }

    public void setPart(TaskPartDto part) {
        this.part = part;
    }
}
