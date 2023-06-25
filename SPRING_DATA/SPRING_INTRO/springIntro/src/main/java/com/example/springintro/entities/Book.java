package com.example.springintro.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book extends BaseEntity{
    @Column(nullable = false, length = 50)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated
    @Column(name = "edition_type", nullable = false)
    private EditionType type;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private int copies;

    @Column(name = "release_date")
    private LocalDate releaseDate;


    @Enumerated
    @Column(nullable = false, name = "age_restriction")
    private AgeRestriction ageRestriction;

    @ManyToOne
    private Author author;

    @ManyToMany
    private Set<Category> categories;

    public Book(){}

    public Book(EditionType editionType, LocalDate releaseDate, int copies, BigDecimal price, AgeRestriction ageRestriction, String title, Author author, Set<Category> categories) {
        this.type = editionType;
        this.releaseDate = releaseDate;
        this.copies = copies;
        this.price = price;
        this.ageRestriction = ageRestriction;
        this.title = title;
        this.author = author;
        this.categories = categories;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EditionType getType() {
        return type;
    }

    public void setType(EditionType type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }


    public AgeRestriction getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(AgeRestriction ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Set<Category> getCategories() {
        return Collections.unmodifiableSet(categories);
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
