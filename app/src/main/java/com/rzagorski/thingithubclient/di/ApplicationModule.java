package com.rzagorski.thingithubclient.di;

import android.app.Application;
import android.content.Context;

import com.rzagorski.thingithubclient.data.ScopeManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Provide application-level dependencies.
 */
@Module
public class ApplicationModule {
    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    public Context provideApplicationContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    public ScopeManager provideScopeCreator(Application application) {
        return new ScopeManager(application);
    }
}
