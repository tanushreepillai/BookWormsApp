package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookResponse {

    @JsonProperty("imageLink") // for V2.0; for V1.0 just default to null?
    private String imageLink;
    @JsonProperty("description")
    private String description;
    @JsonProperty("authors")
    private String author;
    @JsonProperty("title")
    private String title;
    @JsonProperty("finishedReading")
    private boolean finishedReading;
    @JsonProperty("bookId")
    private String bookId;

    public String getImageLinks() {
        return imageLink;
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

    public boolean getFinishedReading() {
        return finishedReading;
    }

    public String getBookId() {
        return bookId;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
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

    public void setFinishedReading(boolean completed) {
        finishedReading = completed;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
