package exam.model.dto;

import com.google.gson.annotations.Expose;
import exam.model.WarrantyType;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.math.BigDecimal;

public class LaptopSeedDto {
    @Expose
    @Length(min = 8)
    @NotNull
    private String macAddress;
    @Expose
    @Positive
    @NotNull
    private double cpuSpeed;

    @Expose
    @Min(8)
    @Max(128)
    @NotNull
    private Integer ram;

    @Expose
    @Min(128)
    @Max(1024)
    @NotNull
    private Integer storage;

    @Expose
    @Length(min = 10)
    @NotNull
    private String description;

    @Expose
    @Positive
    @NotNull
    private BigDecimal price;
    @Expose
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private WarrantyType warrantyType;
    @Expose
    @NotNull
    private ShopWithNameDto shop;

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public double getCpuSpeed() {
        return cpuSpeed;
    }

    public void setCpuSpeed(double cpuSpeed) {
        this.cpuSpeed = cpuSpeed;
    }

    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    public Integer getStorage() {
        return storage;
    }

    public void setStorage(Integer storage) {
        this.storage = storage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public WarrantyType getWarrantyType() {
        return warrantyType;
    }

    public void setWarrantyType(WarrantyType warrantyType) {
        this.warrantyType = warrantyType;
    }

    public ShopWithNameDto getShop() {
        return shop;
    }

    public void setShop(ShopWithNameDto shop) {
        this.shop = shop;
    }
}
