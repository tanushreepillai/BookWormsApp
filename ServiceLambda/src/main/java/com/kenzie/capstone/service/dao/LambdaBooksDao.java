package com.kenzie.capstone.service.dao;

import javax.inject.Inject;

public class LambdaBooksDao {
    // NOT A NECESSARY FILE
//    private DynamoDBMapper mapper;

    @Inject
    public LambdaBooksDao() {}
//    @Inject
//    public BooksDao(DynamoDBMapper mapper) {
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
//    public BooksRecord getBookData(String id) {
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
