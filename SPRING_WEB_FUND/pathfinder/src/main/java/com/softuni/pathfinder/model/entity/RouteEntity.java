package com.softuni.pathfinder.model.entity;

import com.softuni.pathfinder.model.entity.enums.LevelEnum;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "routes")
public class RouteEntity extends BaseEntity {
    @Column(columnDefinition = "LONGTEXT", name = "gpx_coordinates")
    private String gpxCoordinates;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Enumerated(EnumType.STRING)
    private LevelEnum level;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(name = "video_url")
    private String videoUrl;
    @ManyToOne
    private UserEntity author;
    @ManyToMany
    private Set<CategoryEntity> categories;

    @OneToMany(mappedBy = "route")
    private Set<PictureEntity> pictures;

    public String getGpxCoordinates() {
        return gpxCoordinates;
    }

    public void setGpxCoordinates(String gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public void setLevel(LevelEnum level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public Set<CategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryEntity> categories) {
        this.categories = categories;
    }
}
