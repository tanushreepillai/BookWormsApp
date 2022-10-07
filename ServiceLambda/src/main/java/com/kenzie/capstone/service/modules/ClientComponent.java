package com.kenzie.capstone.service.modules;

import com.kenzie.capstone.service.CacheClient;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = CachingModule.class)
public interface ClientComponent {
    /**
     * Method created by Dagger.
     * @return CacheClient object.
     */
    CacheClient buildClient();
}
