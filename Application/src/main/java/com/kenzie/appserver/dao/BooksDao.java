package com.kenzie.appserver.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.kenzie.appserver.backend.models.Books;
import com.kenzie.appserver.repositories.model.BooksRecord;

import javax.inject.Inject;

public class BooksDao {
    private DynamoDBMapper mapper;
    @Inject
    public BooksDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    public Books getBook(String id) {
        BooksRecord bookRecord = new BooksRecord();
        bookRecord.setBookId(id);
        bookRecord = mapper.load(bookRecord);
        return new Books(bookRecord.getImageLink(),
                bookRecord.getDescription(),
                bookRecord.getAuthor(),
                bookRecord.getTitle(),
                bookRecord.getFinishedReading(),
                id);
    }

}
