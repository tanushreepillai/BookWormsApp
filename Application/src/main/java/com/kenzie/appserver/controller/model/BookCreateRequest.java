package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class BookCreateRequest {

    @JsonProperty("imageLinks")
    private String imageLinks;
    @NotEmpty
    @JsonProperty("categories") // on frontend we'll use "genres" as display
    private List<String> categories;
    @NotEmpty
    @JsonProperty("description")
    private String description;
    @NotEmpty
    @JsonProperty("authors")
    private List<String> authors;
    @NotEmpty
    @JsonProperty("title")
    private String title;

    // TODO: Discuss if this needs to be @NotEmpty
    @JsonProperty("infoLink")
    private String infoLink;
    @NotEmpty
    @JsonProperty("isCompleted")
    private boolean isCompleted;

    @NotEmpty
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
