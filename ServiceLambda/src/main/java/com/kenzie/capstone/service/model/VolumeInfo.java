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
    public ImageLinks imageLinks; // todo take smallThumbnail from ImageLinks dto
    @JsonProperty("panelizationSummary")
    public PanelizationSummary panelizationSummary;
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
    public ReadingModes readingModes;
    @JsonProperty("industryIdentifiers")
    public List<IndustryIdentifiers> industryIdentifiers;
    @JsonProperty("description")
    public String description; // todo take this
    @JsonProperty("publishedDate")
    public String publishedDate;
    @JsonProperty("publisher")
    public String publisher;
    @JsonProperty("authors")
    public List<String> authors; // todo take authors[0]
    @JsonProperty("title")
    public String title; // todo take this
}
