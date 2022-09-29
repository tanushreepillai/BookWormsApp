package com.kenzie.capstone.service.dependency;

import com.kenzie.capstone.service.dao.CachingBooksDao;
import com.kenzie.capstone.service.LambdaService;

import dagger.Module;
import dagger.Provides;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Module(
    includes = DaoModule.class
)
public class ServiceModule {

    @Singleton
    @Provides
    @Inject
    public LambdaService provideLambdaService(@Named("CachingBooksDao") CachingBooksDao booksDao) {
        return new LambdaService(booksDao);
    }
}

