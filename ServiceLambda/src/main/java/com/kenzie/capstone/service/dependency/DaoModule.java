package com.kenzie.capstone.service.dependency;

import com.kenzie.capstone.service.dao.LambdaBooksDao;
import dagger.Module;
import dagger.Provides;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Module
public class DaoModule {

    @Singleton
    @Provides
    @Named("LambdaBooksDao")
    @Inject
    public LambdaBooksDao provideBooksDao() {
        return new LambdaBooksDao();
    }
}
