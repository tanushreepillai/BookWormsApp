package com.kenzie.capstone.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BooksData {
    private final String imageLink;
    private final String description;
    private final String author;
    private final String title;
    private final String infoLink;
    private boolean finishedReading;
    private final String bookId;

    public BooksData(String imageLink,
                     String description,
                     String author,
                     String title,
                     String infoLink,
                     String bookId,
                     boolean finishedReading) {
        this.imageLink = imageLink;
        this.description = description;
        this.author = author;
        this.title = title;
        this.infoLink = infoLink;
        this.bookId = bookId;
        this.finishedReading = finishedReading;
    }

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

    public String getInfoLink() {
        return infoLink;
    }

    public boolean finishedReading() {
        return finishedReading;
    }

    public void setFinishedReading(boolean completed) {
        finishedReading = completed;
    }

    public String getBookId() {
        return bookId;
    }
}
