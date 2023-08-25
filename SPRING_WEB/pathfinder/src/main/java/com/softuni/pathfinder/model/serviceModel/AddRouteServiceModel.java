package com.softuni.pathfinder.model.serviceModel;

import com.softuni.pathfinder.model.entity.enums.CategoryEnum;
import com.softuni.pathfinder.model.entity.enums.LevelEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public class AddRouteServiceModel {
    private String name;
    private String description;
    private MultipartFile gpxCoordinates;

    private LevelEnum level;

    private String videoUrl;

    Set<CategoryEnum> categories;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getGpxCoordinates() {
        return gpxCoordinates;
    }

    public void setGpxCoordinates(MultipartFile gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public void setLevel(LevelEnum level) {
        this.level = level;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Set<CategoryEnum> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryEnum> categories) {
        this.categories = categories;
    }
}
