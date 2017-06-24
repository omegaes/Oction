package com.mega4tech.oction.injection;

import android.content.Context;


import com.mega4tech.oction.Application;
import com.mega4tech.oction.model.repository.Repository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    Context getAppContext();

    Application getApp();

    Repository getRepo();
}