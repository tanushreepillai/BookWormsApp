package com.kenzie.capstone.service.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.google.common.collect.ImmutableMap;
import com.kenzie.capstone.service.model.BooksData;
import com.kenzie.capstone.service.model.BooksRecord;
import com.kenzie.capstone.service.model.ExampleRecord;

import java.util.List;

public class BooksDao {
    private DynamoDBMapper mapper;

    public BooksDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    public BooksData storeBooksData(BooksData booksData) {
        try {
            mapper.save(booksData, new DynamoDBSaveExpression()
                    .withExpected(ImmutableMap.of(
                            "id",
                            new ExpectedAttributeValue().withExists(false)
                    )));
        } catch (ConditionalCheckFailedException e) {
            throw new IllegalArgumentException("id has already been used");
        }

        return booksData;

    }

    public List<BooksRecord> getBooksData(String id) {
        BooksRecord booksRecord = new BooksRecord();
        booksRecord.setBookId(id);

        DynamoDBQueryExpression<BooksRecord> queryExpression = new DynamoDBQueryExpression<BooksRecord>()
                .withHashKeyValues(booksRecord)
                .withConsistentRead(false);

        return mapper.query(BooksRecord.class, queryExpression);
    }

    public BooksRecord setBooksData(String id, String data) {
        BooksRecord booksRecord = new BooksRecord();
        booksRecord.setBookId(id);
        try {
            mapper.save(booksRecord, new DynamoDBSaveExpression()
                    .withExpected(ImmutableMap.of(
                            "id",
                            new ExpectedAttributeValue().withExists(false)
                    )));
        } catch (ConditionalCheckFailedException e) {
            throw new IllegalArgumentException("id already exists");
        }

        return booksRecord;
    }

}
