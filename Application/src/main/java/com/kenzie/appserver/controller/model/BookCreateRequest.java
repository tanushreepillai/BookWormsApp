package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class BookCreateRequest {

    @JsonProperty("imageLink")
    private String imageLink;
    @NotEmpty
    @JsonProperty("category") // on frontend we'll use "genres" as display
    private String category;
    @NotEmpty
    @JsonProperty("description")
    private String description;
    @NotEmpty
    @JsonProperty("authors")
    private String author;
    @NotEmpty
    @JsonProperty("title")
    private String title;

    // TODO: Discuss if this needs to be @NotEmpty
    @JsonProperty("infoLink")
    private String infoLink;
    @NotEmpty
    @JsonProperty("finishedReading")
    private boolean finishedReading;

    @NotEmpty
    @JsonProperty("bookId")
    private String bookId;

    public String getImageLink() {
        return imageLink;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getInfoLink() {
        return infoLink;
    }

    public boolean finishedReading() {
        return finishedReading;
    }

    public String getBookId() {
        return bookId;
    }

    public void setImageLinks(String imageLink) {
        this.imageLink = imageLink;
    }

    public void setCategories(List<String> categories) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthors(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setInfoLink(String infoLink) {
        this.infoLink = infoLink;
    }

    public void finishedReading(boolean completed) {
        finishedReading = completed;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

}
