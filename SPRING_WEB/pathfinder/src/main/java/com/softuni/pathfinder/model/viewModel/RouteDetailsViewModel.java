package com.softuni.pathfinder.model.viewModel;

import com.softuni.pathfinder.model.entity.PictureEntity;
import com.softuni.pathfinder.model.entity.enums.LevelEnum;

import java.util.List;
import java.util.Set;

public class RouteDetailsViewModel {
    private Long id;

    private String gpxCoordinates;

    private String description;

    private LevelEnum level;

    private String name;

    private String videoUrl;

    private String authorName;

    private List<PictureViewModel> pictures;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public List<PictureViewModel> getPictures() {
        return pictures;
    }

    public void setPictures(List<PictureViewModel> pictures) {
        this.pictures = pictures;
    }
}
