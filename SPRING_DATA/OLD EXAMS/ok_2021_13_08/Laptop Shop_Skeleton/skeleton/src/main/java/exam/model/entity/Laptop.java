package exam.model.entity;

import exam.model.WarrantyType;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "laptops")
public class Laptop extends BaseEntity {
    @Column(nullable = false, unique = true, name = "mac_address")
    private String macAddress;

    @Column(name = "cpu_speed", nullable = false)
    private double cpuSpeed;

    @Column(nullable = false)
    private Integer ram;
    @Column(nullable = false)
    private Integer storage;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column
    private BigDecimal price;
    @Column
    @Enumerated(value = EnumType.ORDINAL)
    private WarrantyType warrantyType;
    @ManyToOne
    private Shop shop;

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

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    @Override
    public String toString() {
        return String.format("Laptop - %s\n" +
                "*Cpu speed - %.2f\n" +
                "**Ram - %d\n" +
                "***Storage - %d\n" +
                "****Price - %.2f\n" +
                "#Shop name - %s\n" +
                "##Town - %s\n\n",
                macAddress, cpuSpeed, ram, storage, price, getShop().getName(),
                getShop().getTown().getName());
    }
}
