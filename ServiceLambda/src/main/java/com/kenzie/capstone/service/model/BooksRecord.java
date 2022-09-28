package com.kenzie.capstone.service.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.List;

@DynamoDBTable(tableName = "SavedBooks")
public class BooksRecord {
    private String title;
    private String author;
    private String description;
    private String imageLink;
    private String infoLink;
    private boolean isCompleted;
    private String bookId;

    @DynamoDBHashKey(attributeName = "bookId")
    public String getBookId() { return bookId; }

    @DynamoDBAttribute(attributeName = "title")
    public String getTitle() { return title; }

    @DynamoDBAttribute(attributeName = "authors")
    public String getAuthor() { return author; }

    @DynamoDBAttribute(attributeName = "description")
    public String getDescription() { return description; }

    @DynamoDBAttribute(attributeName = "imageLink")
    public String getImageLink() { return imageLink; }

    @DynamoDBAttribute(attributeName="infoLink")
    public String getInfoLink() { return infoLink; }

    @DynamoDBAttribute(attributeName = "title")
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

    public void setInfoLink(String infoLink) {
        this.infoLink = infoLink;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
