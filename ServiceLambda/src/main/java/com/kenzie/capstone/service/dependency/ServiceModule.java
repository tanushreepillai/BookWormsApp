package com.kenzie.capstone.service.dependency;

import com.kenzie.capstone.service.LambdaService;

import dagger.Component;
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
    public LambdaService provideLambdaService() {
        return new LambdaService();
    }
}

