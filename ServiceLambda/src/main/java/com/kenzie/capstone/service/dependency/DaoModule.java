package com.kenzie.capstone.service.dependency;


import com.kenzie.capstone.service.CachingBooksDao;
import com.kenzie.capstone.service.dao.BooksDao;
import com.kenzie.capstone.service.dao.ExampleDao;
import com.kenzie.capstone.service.model.BooksData;
import com.kenzie.capstone.service.util.DynamoDbClientProvider;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dagger.Module;
import dagger.Provides;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.awt.print.Book;

/**
 * Provides DynamoDBMapper instance to DAO classes.
 */
@Module
public class DaoModule {

    @Singleton
    @Provides
    @Named("DynamoDBMapper")
    public DynamoDBMapper provideDynamoDBMapper() {
        return new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient());
    }

    @Singleton
    @Provides
    @Named("BooksDao")
    @Inject
    public BooksDao provideBooksDao(@Named("DynamoDBMapper") DynamoDBMapper mapper) {
        return new BooksDao(mapper);
    }

    @Singleton
    @Provides
    @Named("CachingBooksDao")
    @Inject
    public CachingBooksDao provideCachingBooksDao(@Named("BooksDao")BooksDao booksDao) {
        return new CachingBooksDao(booksDao);
    }

}
