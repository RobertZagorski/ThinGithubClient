package com.rzagorski.thingithubclient.di.preview;

import com.rzagorski.thingithubclient.di.ActivityScope;
import com.rzagorski.thingithubclient.view.preview.PreviewActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Robert Zagórski on 2016-07-22.
 */
@Module
public class PreviewActivityModule {
    private PreviewActivity mPreviewActivity;

    public PreviewActivityModule(PreviewActivity previewActivity) {
        this.mPreviewActivity = previewActivity;
    }

    @Provides
    @ActivityScope
    PreviewActivity providePreviewActivity() {
        return mPreviewActivity;
    }
}
