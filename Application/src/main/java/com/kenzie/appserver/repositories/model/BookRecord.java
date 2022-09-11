package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

@DynamoDBTable(tableName = "Book") //Temporary names for tables and attributes till tables are created
public class BookRecord {


    private String imageLinks;
    private List<String> categories;
    private String description;
    private List<String> authors;
    private String title;
    private String infoLink;
    private boolean isCompleted;
    private String bookId;

    @DynamoDBHashKey(attributeName = "bookId")
    public String getId() {
        return bookId;
    }

    @DynamoDBAttribute(attributeName = "imageLinks")
    public String getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(String imageLinks) {
        this.imageLinks = imageLinks;
    }

    @DynamoDBAttribute(attributeName = "categories")
    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    @DynamoDBAttribute(attributeName = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @DynamoDBAttribute(attributeName = "authorsList")
    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
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

    @DynamoDBAttribute(attributeName = "isCompleted")
    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookRecord that = (BookRecord) o;
        return isCompleted == that.isCompleted && Objects.equals(imageLinks, that.imageLinks) && Objects.equals(categories, that.categories) && Objects.equals(description, that.description) && Objects.equals(authors, that.authors) && Objects.equals(title, that.title) && Objects.equals(infoLink, that.infoLink) && Objects.equals(bookId, that.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageLinks, categories, description, authors, title, infoLink, isCompleted, bookId);
    }
}
