package com.kenzie.appserver.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {
    private Items items = new Items();
//    @JsonProperty("canonicalVolumeLink")
//    private String canonicalVolumeLink;
//    @JsonProperty("infoLink")
//    private String infoLink;
//    @JsonProperty("previewLink")
//    private String previewLink;
//    @JsonProperty("language")
//    private String language;
    @JsonProperty("imageLinks")
    private ImageLinks imageLinks;
//    @JsonProperty("panelizationSummary")
//    private PanelizationSummary panelizationSummary;
//    @JsonProperty("contentVersion")
//    private String contentVersion;
//    @JsonProperty("allowAnonLogging")
//    private boolean allowAnonLogging;
//    @JsonProperty("maturityRating")
//    private String maturityRating;
//    @JsonProperty("ratingsCount")
//    private int ratingsCount;
    @JsonProperty("averageRating")
    private int averageRating;
    @JsonProperty("categories")
    private List<String> categories;
//    @JsonProperty("printType")
//    private String printType;
    @JsonProperty("pageCount")
    private int pageCount;
//    @JsonProperty("readingModes")
//    private ReadingModes readingModes;
//    @JsonProperty("industryIdentifiers")
//    private List<IndustryIdentifiers> industryIdentifiers;
    @JsonProperty("description")
    private String description;
    @JsonProperty("publishedDate")
    private String publishedDate;
    @JsonProperty("publisher")
    private String publisher;
    @JsonProperty("authors")
    private List<String> authors;
    @JsonProperty("title")
    private String title;
    @JsonProperty("subtitle")
    private String subtitle;

//    public String getCanonicalVolumeLink() {
//        return canonicalVolumeLink;
//    }
//
//    public void setCanonicalVolumeLink(String canonicalVolumeLink) {
//        this.canonicalVolumeLink = canonicalVolumeLink;
//    }
//
//    public String getInfoLink() {
//        return infoLink;
//    }
//
//    public void setInfoLink(String infoLink) {
//        this.infoLink = infoLink;
//    }
//
//    public String getPreviewLink() {
//        return previewLink;
//    }
//
//    public void setPreviewLink(String previewLink) {
//        this.previewLink = previewLink;
//    }
//
//    public String getLanguage() {
//        return language;
//    }
//
//    public void setLanguage(String language) {
//        this.language = language;
//    }

    public ImageLinks getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(ImageLinks imageLinks) {
        this.imageLinks = imageLinks;
    }

//    public PanelizationSummary getPanelizationSummary() {
//        return panelizationSummary;
//    }
//
//    public void setPanelizationSummary(PanelizationSummary panelizationSummary) {
//        this.panelizationSummary = panelizationSummary;
//    }
//
//    public String getContentVersion() {
//        return contentVersion;
//    }
//
//    public void setContentVersion(String contentVersion) {
//        this.contentVersion = contentVersion;
//    }
//
//    public boolean getAllowAnonLogging() {
//        return allowAnonLogging;
//    }
//
//    public void setAllowAnonLogging(boolean allowAnonLogging) {
//        this.allowAnonLogging = allowAnonLogging;
//    }
//
//    public String getMaturityRating() {
//        return maturityRating;
//    }
//
//    public void setMaturityRating(String maturityRating) {
//        this.maturityRating = maturityRating;
//    }
//
//    public int getRatingsCount() {
//        return ratingsCount;
//    }
//
//    public void setRatingsCount(int ratingsCount) {
//        this.ratingsCount = ratingsCount;
//    }

    public int getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(int averageRating) {
        this.averageRating = averageRating;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

//    public String getPrintType() {
//        return printType;
//    }
//
//    public void setPrintType(String printType) {
//        this.printType = printType;
//    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

//    public ReadingModes getReadingModes() {
//        return readingModes;
//    }
//
//    public void setReadingModes(ReadingModes readingModes) {
//        this.readingModes = readingModes;
//    }
//
//    public List<IndustryIdentifiers> getIndustryIdentifiers() {
//        return industryIdentifiers;
//    }
//
//    public void setIndustryIdentifiers(List<IndustryIdentifiers> industryIdentifiers) {
//        this.industryIdentifiers = industryIdentifiers;
//    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    @Override
    public String toString() {
        return "Book{" +
                "\nimageLinks=" + imageLinks.getSmallThumbnail() +
                "\naverageRating=" + averageRating +
                "\ncategories=" + categories +
                "\npageCount=" + pageCount +
                "\ndescription='" + description + '\'' +
                "\npublishedDate='" + publishedDate + '\'' +
                "\npublisher='" + publisher + '\'' +
                "\nauthors=" + authors +
                "\ntitle='" + title + '\'' +
                "\nsubtitle='" + subtitle + '\'' +
                "\nselflink='" + items.getSelfLink() + '\'' +
                '}';
    }
}
