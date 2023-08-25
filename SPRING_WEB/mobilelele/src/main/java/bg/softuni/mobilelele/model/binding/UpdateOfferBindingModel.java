package bg.softuni.mobilelele.model.binding;

import bg.softuni.mobilelele.model.entity.enums.EngineEnum;
import bg.softuni.mobilelele.model.entity.enums.TransmissionEnum;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class UpdateOfferBindingModel {
    private Long id;
    private String modelName;
    private String modelBrandName;
    @NotNull
    private EngineEnum engine;
    @NotNull
    @Min(1930)
    private Integer year;
    @NotNull
    @Min(100)
    private BigDecimal price;
    @NotBlank
    private String description;
    @NotNull
    private TransmissionEnum transmission;
    @PositiveOrZero
    private Integer mileage;
    @NotBlank
    private String imageUrl;


    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EngineEnum getEngine() {
        return engine;
    }

    public void setEngine(EngineEnum engine) {
        this.engine = engine;
    }

    public TransmissionEnum getTransmission() {
        return transmission;
    }

    public void setTransmission(TransmissionEnum transmission) {
        this.transmission = transmission;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelBrandName() {
        return modelBrandName;
    }

    public void setModelBrandName(String modelBrandName) {
        this.modelBrandName = modelBrandName;
    }
}
