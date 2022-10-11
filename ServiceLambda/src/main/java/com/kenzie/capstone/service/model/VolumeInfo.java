package com.kenzie.capstone.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class VolumeInfo {
    @JsonProperty("canonicalVolumeLink")
    public String canonicalVolumeLink;
    @JsonProperty("infoLink")
    public String infoLink;
    @JsonProperty("previewLink")
    public String previewLink;
    @JsonProperty("language")
    public String language;
    @JsonProperty("imageLinks")
    public ImageLinks imageLinks;
    @JsonProperty("panelizationSummary")
    public com.kenzie.capstone.service.model.PanelizationSummary panelizationSummary;
    @JsonProperty("contentVersion")
    public String contentVersion;
    @JsonProperty("allowAnonLogging")
    public boolean allowAnonLogging;
    @JsonProperty("maturityRating")
    public String maturityRating;
    @JsonProperty("ratingsCount")
    public int ratingsCount;
    @JsonProperty("averageRating")
    public double averageRating;
    @JsonProperty("categories")
    public List<String> categories;
    @JsonProperty("printType")
    public String printType;
    @JsonProperty("pageCount")
    public int pageCount;
    @JsonProperty("readingModes")
    public com.kenzie.capstone.service.model.ReadingModes readingModes;
    @JsonProperty("industryIdentifiers")
    public List<com.kenzie.capstone.service.model.IndustryIdentifiers> industryIdentifiers;
    @JsonProperty("description")
    public String description;
    @JsonProperty("publishedDate")
    public String publishedDate;
    @JsonProperty("publisher")
    public String publisher;
    @JsonProperty("author")
    public List<String> authors;
    @JsonProperty("title")
    public String title;
}
