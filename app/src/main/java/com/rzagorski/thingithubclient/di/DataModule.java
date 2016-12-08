package com.rzagorski.thingithubclient.di;

import android.app.Application;

import dagger.Module;

/**
 * Created by Robert Zagórski on 2016-06-28.
 */
@Module
public class DataModule {
    private final Application mApplication;

    public DataModule(Application application) {
        mApplication = application;
    }
}
