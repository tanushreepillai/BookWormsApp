package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookResponse {

    @JsonProperty("imageLinks") // for V2.0; for V1.0 just default to null?
    private String imageLink;
    @JsonProperty("categories") // on frontend we'll use "genres" as display
    private String category;
    @JsonProperty("description")
    private String description;
    @JsonProperty("authors")
    private String author;
    @JsonProperty("title")
    private String title;
    @JsonProperty("infoLink")
    private String infoLink;
    @JsonProperty("finishedReading")
    private boolean finishedReading;
    @JsonProperty("bookId")
    private String bookId;

    public String getImageLinks() {
        return imageLink;
    }

    public String getCategories() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthors() {
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

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public void setCategories(String category) {
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
