package com.kenzie.capstone.service.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.List;

@DynamoDBTable(tableName = "SavedBooks")
public class BooksRecord {
    private String title;
    private List<String> authors;
    private String description;
    private List<String> categories;
    private String imageLinks;
    private String infoLink;
    private boolean isCompleted;
    private String bookId;

    @DynamoDBHashKey(attributeName = "bookId")
    public String getBookId() { return bookId; }

    @DynamoDBAttribute(attributeName = "title")
    public String getTitle() { return title; }

    @DynamoDBAttribute(attributeName = "authors")
    public List<String> getAuthors() { return authors; }

    @DynamoDBAttribute(attributeName = "description")
    public String getDescription() { return description; }

    @DynamoDBAttribute(attributeName = "categories")
    public List<String> getCategories() { return categories; }

    @DynamoDBAttribute(attributeName = "imageLinks")
    public String getImageLinks() { return imageLinks; }

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

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public void setImageLinks(String imageLinks) {
        this.imageLinks = imageLinks;
    }

    public void setInfoLink(String infoLink) {
        this.infoLink = infoLink;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
