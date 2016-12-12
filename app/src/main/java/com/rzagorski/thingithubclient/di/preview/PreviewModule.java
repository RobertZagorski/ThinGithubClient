package com.rzagorski.thingithubclient.di.preview;


import com.rzagorski.thingithubclient.model.app.GithubUser;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Robert Zagórski on 07.06.2016.
 */
@Module
public class PreviewModule {
    private GithubUser mGithubUser;

    public PreviewModule(GithubUser githubUser) {
        this.mGithubUser = githubUser;
    }

    @Provides
    @PreviewScope
    GithubUser provideConfiguration() {
        if (mGithubUser == null) {
            mGithubUser = new GithubUser();
        }
        return mGithubUser;
    }

}