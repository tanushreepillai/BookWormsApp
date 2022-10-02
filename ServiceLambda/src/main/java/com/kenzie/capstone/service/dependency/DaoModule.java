package com.kenzie.capstone.service.dependency;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.kenzie.capstone.service.dao.BooksDao;
import com.kenzie.capstone.service.utilities.DynamoDbClientProvider;
import dagger.Module;
import dagger.Provides;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Module
public class DaoModule {

    @Singleton
    @Provides
    @Named ("DynamoDbMapper")
    @Inject
    public DynamoDBMapper provideDynamoDBMapper() {
        return new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient());
    }

    @Singleton
    @Provides
    @Named("BooksDao")
    @Inject
    public BooksDao provideBooksDao(DynamoDBMapper dynamoDBMapper) {
        return new BooksDao(dynamoDBMapper);
    }
}
