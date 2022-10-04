package com.kenzie.appserver.dependency;

import com.kenzie.appserver.activity.GetBookActivity;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = DaoModule.class)
public interface ServiceComponent {
    GetBookActivity provideGetBookActivity();
}
