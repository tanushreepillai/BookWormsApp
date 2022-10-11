package com.kenzie.appserver.dependency;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.kenzie.appserver.dao.BooksDao;
import com.kenzie.appserver.dao.CachingBooksDao;
import com.kenzie.appserver.utilities.DynamoDbClientProvider;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;


/**
 * Provides DynamoDBMapper instance to DAO classes.
 */

public class DaoModule {

    @Singleton
//    @Provides
//    @Named("DynamoDBMapper")
    public DynamoDBMapper provideDynamoDBMapper() {
        return new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient());
    }

//    @Singleton
//    @Provides
//    @Named("BooksDao")
//    @Inject
//    public BooksDao provideBooksDao(@Named("DynamoDBMapper") DynamoDBMapper mapper) {
//        return new BooksDao(mapper);
//    }
//
    @Singleton
//    @Provides
    @Named("CachingBooksDao")
    @Inject
    public CachingBooksDao provideBooksDao(@Named("BooksDao") BooksDao booksDao) {
        return new CachingBooksDao(booksDao);
    }


}
