package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class BookCreateRequest {

    @JsonProperty("imageLink")
    private String imageLink;
    @NotEmpty
    @JsonProperty("description")
    private String description;
    @NotEmpty
    @JsonProperty("author")
    private String author;
    @NotEmpty
    @JsonProperty("title")
    private String title;

    // TODO: Discuss if this needs to be @NotEmpty
    @NotEmpty
    @JsonProperty("finishedReading")
    private boolean finishedReading;

    @NotEmpty
    @JsonProperty("bookId")
    private String bookId;

    public String getImageLink() {
        return imageLink;
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

    public boolean finishedReading() {
        return finishedReading;
    }

    public String getBookId() {
        return bookId;
    }

    public void setImageLinks(String imageLink) {
        this.imageLink = imageLink;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void finishedReading(boolean completed) {
        finishedReading = completed;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

}
