package com.kenzie.appserver.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {
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

    public Book(String imageLinks, List<String> categories, String description, List<String> authors, String title, String infoLink, boolean isCompleted, String bookId) {
        this.imageLinks = imageLinks;
        this.categories = categories;
        this.description = description;
        this.authors = authors;
        this.title = title;
        this.infoLink = infoLink;
        this.isCompleted = isCompleted;
        this.bookId = bookId;
    }

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

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public String getBookId() {
        return bookId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "imageLinks='" + imageLinks + '\'' +
                ", categories=" + categories +
                ", description='" + description + '\'' +
                ", authors=" + authors +
                ", title='" + title + '\'' +
                ", infoLink='" + infoLink + '\'' +
                ", isCompleted=" + isCompleted +
                ", bookId='" + bookId + '\'' +
                '}';
    }
}
