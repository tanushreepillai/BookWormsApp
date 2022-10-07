package com.kenzie.capstone.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImageLinks {
    @JsonProperty("smallThumbnail")
    private String smallThumbnail;

    public ImageLinks() {
    }

    public ImageLinks(String smallThumbnail) {
        this.smallThumbnail = smallThumbnail;
    }

    public String getSmallThumbnail() {
        return smallThumbnail;
    }

    public void setSmallThumbnail(String smallThumbnail) {
        this.smallThumbnail = smallThumbnail;
    }
}
