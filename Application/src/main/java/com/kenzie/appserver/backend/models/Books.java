package com.kenzie.appserver.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Books {
    @JsonProperty("imageLink") // for V2.0; for V1.0 just default to null?
    private String imageLink;
    @JsonProperty("description")
    private String description;
    @JsonProperty("author")
    private String author;
    @JsonProperty("title")
    private String title;
    @JsonProperty("infoLink")
    private String infoLink;
    @JsonProperty("finishedReading")
    private boolean finishedReading;
    @JsonProperty("bookId")
    private String bookId;

    public Books(String imageLink, String description, String author, String title, String infoLink, boolean finishedReading, String bookId) {
        this.imageLink = imageLink;
        this.description = description;
        this.author = author;
        this.title = title;
        this.infoLink = infoLink;
        this.finishedReading = finishedReading;
        this.bookId = bookId;
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

    @Override
    public String toString() {
        return "Book{" +
                "imageLink='" + imageLink + '\'' +
                ", description='" + description + '\'' +
                ", author=" + author +
                ", title='" + title + '\'' +
                ", infoLink='" + infoLink + '\'' +
                ", finishedReading=" + finishedReading +
                ", bookId='" + bookId + '\'' +
                '}';
    }
}
