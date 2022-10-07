package com.kenzie.capstone.service.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.google.common.collect.ImmutableMap;
import com.kenzie.capstone.service.model.BooksRecord;

import javax.inject.Inject;
import java.util.List;

public class LambdaBooksDao {
    // NOT A NECESSARY FILE
//    private DynamoDBMapper mapper;
//
//    @Inject
//    public LambdaBooksDao() {}
//    @Inject
//    public LambdaBooksDao(DynamoDBMapper mapper) {
//        this.mapper = mapper;
//    }

//    public BooksData storeBooksData(BooksData booksData) {
//        try {
//            mapper.save(booksData, new DynamoDBSaveExpression()
//                    .withExpected(ImmutableMap.of(
//                            "id",
//                            new ExpectedAttributeValue().withExists(false)
//                    )));
//        } catch (ConditionalCheckFailedException e) {
//            throw new IllegalArgumentException("id has already been used");
//        }
//
//        return booksData;
//
//    }

//    Figure out how to map
//    public List<BooksRecord> getBookData(String id) {
//        return null;
//    }

//    public BooksRecord setBooksData(String id, String data) {
//        BooksRecord booksRecord = new BooksRecord();
//        booksRecord.setBookId(id);
//        try {
//            mapper.save(booksRecord, new DynamoDBSaveExpression()
//                    .withExpected(ImmutableMap.of(
//                            "id",
//                            new ExpectedAttributeValue().withExists(false)
//                    )));
//        } catch (ConditionalCheckFailedException e) {
//            throw new IllegalArgumentException("id already exists");
//        }
//
//        return booksRecord;
//    }

}
