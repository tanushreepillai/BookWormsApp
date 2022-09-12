package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookResponse {

    @JsonProperty("imageLinks") // for V2.0; for V1.0 just default to null?
    private String imageLinks;
    @JsonProperty("categories") // on frontend we'll use "genres" as display
    private List<String> categories;
    @JsonProperty("description")
    private String description;
    @JsonProperty("authors")
    private List<String> authors;
    @JsonProperty("title")
    private String title;
    @JsonProperty("infoLink")
    private String infoLink;
    @JsonProperty("isCompleted")
    private boolean isCompleted;
    @JsonProperty("bookId")
    private String bookId;

    public String getImageLinks() {
        return imageLinks;
    }

    public List<String> getCategories() {
        return categories;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public String getTitle() {
        return title;
    }

    public String getInfoLink() {
        return infoLink;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public String getBookId() {
        return bookId;
    }

    public void setImageLinks(String imageLinks) {
        this.imageLinks = imageLinks;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setInfoLink(String infoLink) {
        this.infoLink = infoLink;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
