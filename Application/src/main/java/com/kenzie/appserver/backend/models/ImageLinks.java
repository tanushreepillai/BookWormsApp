package com.kenzie.appserver.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImageLinks {
    @JsonProperty("thumbnail")
    private String thumbnail;
    @JsonProperty("smallThumbnail")
    private String smallThumbnail;

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getSmallThumbnail() {
        return smallThumbnail;
    }

    public void setSmallThumbnail(String smallThumbnail) {
        this.smallThumbnail = smallThumbnail;
    }
}
