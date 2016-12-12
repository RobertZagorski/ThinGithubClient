package com.rzagorski.thingithubclient.data;

import android.app.Application;
import android.support.annotation.Nullable;

import com.rzagorski.thingithubclient.ThinGithubClientApplication;
import com.rzagorski.thingithubclient.di.preview.PreviewComponent;
import com.rzagorski.thingithubclient.di.preview.PreviewModule;
import com.rzagorski.thingithubclient.model.app.GithubUser;

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

    @Nullable
    public PreviewComponent getUserComponent() {
        return mApplication.getPreviewComponent();
    }

    public void releasePreviewComponent() {
        mApplication.setComponent((PreviewComponent) null);
    }

    public PreviewComponent createPreviewComponent(@Nullable GithubUser githubUser) {
        PreviewComponent configurationComponent = mApplication.getApplicationComponent().provide(new PreviewModule(githubUser));
        mApplication.setComponent(configurationComponent);
        return configurationComponent;
    }
}
