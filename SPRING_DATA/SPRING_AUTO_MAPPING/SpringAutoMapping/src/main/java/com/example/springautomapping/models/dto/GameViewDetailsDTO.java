package com.example.springautomapping.models.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class GameViewDetailsDTO {
    private String title;
    private BigDecimal price;
    private String description;
    private LocalDate releaseDate;

    public GameViewDetailsDTO(String title, BigDecimal price, String description, LocalDate releaseDate) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public GameViewDetailsDTO() {
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

    @Override
    public String toString() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = formatter.format(this.releaseDate);

        return String.format("Title: %s%nPrice: %.2f%nDescription: %s%nRelease date: %s",
                this.title, this.price, this.description, formattedDate);
    }
}
