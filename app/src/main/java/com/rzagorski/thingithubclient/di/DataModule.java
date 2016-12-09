package com.rzagorski.thingithubclient.di;

import android.app.Application;

import com.rzagorski.thingithubclient.data.api.ApiManager;
import com.rzagorski.thingithubclient.data.api.GithubApi;
import com.rzagorski.thingithubclient.data.api.retrofit.RetrofitApiFactory;
import com.rzagorski.thingithubclient.data.api.retrofit.RetrofitApiManagerImpl;

import javax.inject.Named;
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
    @Named("baseUrl")
    String provideApiEndpoint() {
        return GithubApi.BASE_URL;
    }

    @Provides
    @Singleton
    GithubApi provideGithubApi(@Named("baseUrl") String url) {
        return RetrofitApiFactory.makeApiManager(url);
    }

    @Provides
    @Singleton
    ApiManager provideApiManager(GithubApi githubApi) {
        return new RetrofitApiManagerImpl(githubApi);
    }
}
