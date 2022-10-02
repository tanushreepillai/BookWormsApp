package com.kenzie.capstone.service.dependency;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.kenzie.capstone.service.LambdaService;

import com.kenzie.capstone.service.dao.BooksDao;
import dagger.Component;

import javax.inject.Singleton;

/**
 * Declares the dependency roots that Dagger will provide.
 */
@Singleton
@Component(modules = {DaoModule.class, ServiceModule.class})
public interface ServiceComponent {
    
    LambdaService provideLambdaService();
}
