package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;

@DynamoDBTable(tableName = "FavoriteBooks") //Temporary names for tables and attributes till tables are created
public class BooksRecord {

    private String imageLink;
    private String description;
    private String author;
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

    @DynamoDBAttribute(attributeName = "author")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String authorsList) {
        this.author = authorsList;
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

        if (finishedReading != that.finishedReading) return false;
        if (imageLink != null ? !imageLink.equals(that.imageLink) : that.imageLink != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (author != null ? !author.equals(that.author) : that.author != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        return bookId != null ? bookId.equals(that.bookId) : that.bookId == null;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
