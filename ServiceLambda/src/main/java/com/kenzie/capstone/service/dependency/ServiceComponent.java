package com.kenzie.capstone.service.dependency;

import com.kenzie.capstone.service.LambdaService;

import dagger.Component;

import javax.inject.Singleton;

/**
 * Declares the dependency roots that Dagger will provide.
 */
@Singleton
@Component(modules = {ServiceModule.class})
public interface ServiceComponent {
    LambdaService provideLambdaService();
}
