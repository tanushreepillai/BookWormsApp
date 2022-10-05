package com.kenzie.capstone.service.model;

public class LambdaBooksRecord {
    private String title;
    private String author;
    private String description;
    private String imageLink;
    private boolean isCompleted;
    private String bookId;

    public String getBookId() { return bookId; }

    public String getTitle() { return title; }

    public String getAuthor() { return author; }

    public String getDescription() { return description; }

    public String getImageLink() { return imageLink; }

    public boolean isCompleted() { return isCompleted; }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
