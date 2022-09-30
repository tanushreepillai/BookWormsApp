package com.kenzie.appserver.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.kenzie.appserver.backend.models.Books;
import com.kenzie.appserver.repositories.model.BookRecord;

import javax.inject.Inject;

public class BooksDao {
    private DynamoDBMapper mapper;

    @Inject
    public BooksDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    public Books getBook(String id) {
        BookRecord bookRecord = new BookRecord();
        bookRecord.setBookId(id);
        bookRecord = mapper.load(bookRecord);
        return new Books(bookRecord.getImageLink(),
                bookRecord.getDescription(),
                bookRecord.getAuthor(),
                bookRecord.getTitle(),
                bookRecord.finishedReading(),
                id);
    }

}
