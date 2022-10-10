package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;

@DynamoDBTable(tableName = "FavoriteBooks") //Temporary names for tables and attributes till tables are created
public class BooksRecord {

    private String imageLink;
    private String description;
    private String authorsList;
    private String title;
    private boolean finishedReading;
    private String bookId;

    @DynamoDBHashKey(attributeName = "bookId")
    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    @DynamoDBAttribute(attributeName = "imageLink")
    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    @DynamoDBAttribute(attributeName = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @DynamoDBAttribute(attributeName = "authorsList")
    public String getAuthorsList() {
        return authorsList;
    }

    public void setAuthorsList(String authorsList) {
        this.authorsList = authorsList;
    }

    @DynamoDBAttribute(attributeName = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @DynamoDBAttribute(attributeName = "finishedReading")
    public boolean getFinishedReading() {
        return finishedReading;
    }

    public void setFinishedReading(boolean finishedReading) {
        this.finishedReading = finishedReading;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BooksRecord that = (BooksRecord) o;
        return finishedReading == that.finishedReading && Objects.equals(imageLink, that.imageLink) && Objects.equals(description, that.description) && Objects.equals(author, that.author) && Objects.equals(title, that.title) && Objects.equals(bookId, that.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageLink, description, author, title, finishedReading, bookId);
    }
}
