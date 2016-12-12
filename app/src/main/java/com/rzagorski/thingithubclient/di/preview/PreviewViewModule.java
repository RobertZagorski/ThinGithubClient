package com.rzagorski.thingithubclient.di.preview;

import com.rzagorski.thingithubclient.view.preview.Preview;
import com.rzagorski.thingithubclient.view.preview.PreviewFragment;
import com.rzagorski.thingithubclient.view.preview.PreviewPresenterImpl;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Robert Zagórski on 2016-12-12.
 */
@Module
public abstract class PreviewViewModule {

    @Binds
    public abstract Preview.View providePreviewView(PreviewFragment previewFragment);

    @Binds
    public abstract Preview.Presenter providePreviewPresenter(PreviewPresenterImpl previewPresenter);
}
