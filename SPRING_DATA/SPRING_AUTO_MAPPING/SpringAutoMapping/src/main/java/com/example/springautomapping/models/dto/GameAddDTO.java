package com.example.springautomapping.models.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GameAddDTO {

    @Pattern(regexp = "^[A-Z]{1}[A-Za-z0-9]{1,99}", message = "Incorrect title!")
    private String title;

    @Positive(message = "Price must be positive number!")
    private BigDecimal price;

    @Positive(message = "Size must be positive number!")
    private Double size;

    //@Pattern(regexp = "^(https:\\/\\/www.youtube.com){1}", message = "Only videos from www.youtube.com are allowed")
    private String trailer;

    @Pattern(regexp = "(^https:\\/\\/)|(^http:\\/\\/)", message = "URL must start with http:// or https://")
    private String thumbnailURL;

    @Size(min = 20, message = "Description must be at least 20 symbols")
    private String description;
    private LocalDate releaseDate;

    public GameAddDTO(String title, BigDecimal price, Double size, String trailer, String thumbnailURL, String description, LocalDate releaseDate) {
        this.title = title;
        this.price = price;
        this.size = size;
        this.trailer = trailer;
        this.thumbnailURL = thumbnailURL;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public String getTrailer() {
       return this.trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
