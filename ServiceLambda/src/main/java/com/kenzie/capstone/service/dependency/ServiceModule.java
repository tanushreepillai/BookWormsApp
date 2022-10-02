package com.kenzie.capstone.service.dependency;

import com.kenzie.capstone.service.LambdaService;

import com.kenzie.capstone.service.dao.LambdaBooksDao;
import dagger.Module;
import dagger.Provides;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Module
public class ServiceModule {

    @Singleton
    @Provides
    @Inject
    public LambdaService provideLambdaService(@Named("LambdaBooksDao") LambdaBooksDao lambdaBooksDao) {
        return new LambdaService(lambdaBooksDao);
    }
}

