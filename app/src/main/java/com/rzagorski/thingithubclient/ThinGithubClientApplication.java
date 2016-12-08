package com.rzagorski.thingithubclient;

import android.app.Application;

import com.rzagorski.thingithubclient.di.ApplicationComponent;
import com.rzagorski.thingithubclient.di.ApplicationModule;
import com.rzagorski.thingithubclient.di.DaggerApplicationComponent;
import com.rzagorski.thingithubclient.di.DataModule;

/**
 * Created by Robert Zagórski on 2016-12-08.
 */

public class ThinGithubClientApplication extends Application {

    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initDagger();
    }

    private void initDagger() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .dataModule(new DataModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
