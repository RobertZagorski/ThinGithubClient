package com.rzagorski.thingithubclient.di;

import android.app.Application;

import com.rzagorski.thingithubclient.data.api.ApiManager;
import com.rzagorski.thingithubclient.data.api.retrofit.RetrofitApiManagerImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Robert Zagórski on 2016-06-28.
 */
@Module
public class DataModule {
    private final Application mApplication;

    public DataModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    ApiManager provideApiManager() {
        return new RetrofitApiManagerImpl();
    }
}
