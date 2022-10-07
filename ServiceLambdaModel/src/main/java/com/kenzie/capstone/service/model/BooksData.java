package com.kenzie.capstone.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BooksData {
    // have default constructor
    // getters and setters
    @JsonProperty("volumeInfo")
    private VolumeInfo volumeInfo;

    @JsonProperty("imageLinks")
    private ImageLinks imageLinks;

    public BooksData() {
    }

    public BooksData(VolumeInfo volumeInfo, ImageLinks imageLinks) {
        this.volumeInfo = volumeInfo;
        this.imageLinks = imageLinks;
    }

    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(VolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }

    public ImageLinks getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(ImageLinks imageLinks) {
        this.imageLinks = imageLinks;
    }
}
