package com.kenzie.capstone.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BooksData {
    private String imageLinks;
    private List<String> categories;
    private String description;
    private List<String> authors;
    private String title;
    private String infoLink;
    private boolean isCompleted;
    private String bookId;

    public BooksData(String imageLinks,
                     String description,
                     List<String> authors,
                     String title,
                     String infoLink,
                     String bookId) {
        this.imageLinks = imageLinks;
        this.description = description;
        this.authors = authors;
        this.title = title;
        this.infoLink = infoLink;
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
}
