package com.softuni.pathfinder.model.viewModel;

import java.util.List;


public class RouteViewModel {
    private Long id;
    private String videoUrl;
    private String name;
    private String description;
    private List<PictureViewModel> pictures;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

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

    public List<PictureViewModel> getPictures() {
        return pictures;
    }

    public void setPictures(List<PictureViewModel> pictures) {
        this.pictures = pictures;
    }
}
