package com.kenzie.capstone.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VolumeInfo {
    @JsonProperty("title")
    private String title;
    @JsonProperty("author")
    private String[] author;
    @JsonProperty("description")
    private String description;
    @JsonProperty("imageLinks")
    private ImageLinks imageLinks;

    public VolumeInfo() {
    }

    public VolumeInfo(String title, String[] author, String description, ImageLinks imageLinks) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.imageLinks = imageLinks;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getAuthor() {
        return author;
    }

    public void setAuthor(String[] author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ImageLinks getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(ImageLinks imageLinks) {
        this.imageLinks = imageLinks;
    }
}
