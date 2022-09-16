package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

@DynamoDBTable(tableName = "Book") //Temporary names for tables and attributes till tables are created
public class BookRecord {


    private String imageLink;
    private String category;
    private String description;
    private String author;
    private String title;
    private String infoLink;
    private boolean finishedReading;
    private String bookId;

    @DynamoDBHashKey(attributeName = "bookId")
    public String getId() {
        return bookId;
    }

    @DynamoDBAttribute(attributeName = "imageLink")
    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    @DynamoDBAttribute(attributeName = "categories")
    public String getCategory() {
        return category;
    }

    public void setCategories(String category) {
        this.category = category;
    }

    @DynamoDBAttribute(attributeName = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @DynamoDBAttribute(attributeName = "authorsList")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @DynamoDBAttribute(attributeName = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @DynamoDBAttribute(attributeName = "infoLink")
    public String getInfoLink() {
        return infoLink;
    }

    public void setInfoLink(String infoLink) {
        this.infoLink = infoLink;
    }

    @DynamoDBAttribute(attributeName = "finishedReading")
    public boolean finishedReading() {
        return finishedReading;
    }

    public void finishedReading(boolean completed) {
        finishedReading = completed;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookRecord that = (BookRecord) o;
        return finishedReading == that.finishedReading && Objects.equals(imageLink, that.imageLink) && Objects.equals(category, that.category) && Objects.equals(description, that.description) && Objects.equals(author, that.author) && Objects.equals(title, that.title) && Objects.equals(infoLink, that.infoLink) && Objects.equals(bookId, that.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageLink, category, description, author, title, infoLink, finishedReading, bookId);
    }
}
