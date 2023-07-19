package exam.model.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "shop")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShopSeedDTO {
    @XmlElement
    @Length(min = 4)
    @NotNull
    private String address;
    @XmlElement(name = "employee-count")
    @Min(1)
    @Max(50)
    @NotNull
    private Integer employeeCount;
    @XmlElement
    @Min(20000)
    @NotNull
    private BigDecimal income;
    @XmlElement
    @Length(min = 4)
    @NotNull
    private String name;
    @XmlElement(name = "shop-area")
    @Min(150)
    @NotNull
    private double shopArea;
    @XmlElement(name = "town")
    @NotNull
    private TownSeedNameDto town;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(Integer employeeCount) {
        this.employeeCount = employeeCount;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public double getShopArea() {
        return shopArea;
    }

    public void setShopArea(double shopArea) {
        this.shopArea = shopArea;
    }

    public TownSeedNameDto getTown() {
        return town;
    }

    public void setTown(TownSeedNameDto town) {
        this.town = town;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
