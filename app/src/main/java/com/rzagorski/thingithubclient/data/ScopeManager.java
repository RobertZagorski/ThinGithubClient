package com.rzagorski.thingithubclient.data;

import android.app.Application;

import com.rzagorski.thingithubclient.ThinGithubClientApplication;

import javax.inject.Inject;

/**
 * Created by Robert Zagórski on 2016-07-22.
 */
public class ScopeManager {
    private ThinGithubClientApplication mApplication;

    @Inject
    public ScopeManager(Application application) {
        mApplication = (ThinGithubClientApplication) application;
    }

}
