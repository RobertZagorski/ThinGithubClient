package com.rzagorski.thingithubclient.di.preview;

import com.rzagorski.thingithubclient.di.ActivityScope;
import com.rzagorski.thingithubclient.model.app.GithubUser;
import com.rzagorski.thingithubclient.view.preview.Preview;
import com.rzagorski.thingithubclient.view.preview.PreviewActivity;
import com.rzagorski.thingithubclient.view.preview.PreviewFragment;
import com.rzagorski.thingithubclient.view.preview.PreviewPresenterImpl;

import dagger.Lazy;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Robert Zagórski on 2016-07-22.
 */
@Module(
        includes = PreviewViewModule.class
)
public class PreviewActivityModule {
    private PreviewActivity mPreviewActivity;
    private static PreviewFragment previewFragment;

    public PreviewActivityModule(PreviewActivity previewActivity) {
        this.mPreviewActivity = previewActivity;
    }

    @Provides
    @ActivityScope
    PreviewActivity providePreviewActivity() {
        return mPreviewActivity;
    }

    @Provides
    @ActivityScope
    PreviewFragment providePreviewFragment() {
        if (previewFragment == null) {
            previewFragment = new PreviewFragment();
        }
        return previewFragment;
    }

    @Provides
    @ActivityScope
    PreviewPresenterImpl providePreviewPresenterImpl(Lazy<Preview.View> viewProvider, GithubUser githubUser) {
        return new PreviewPresenterImpl(viewProvider, githubUser);
    }
}
