package com.rzagorski.thingithubclient.di.preview;

import com.rzagorski.thingithubclient.di.ActivityScope;
import com.rzagorski.thingithubclient.view.preview.PreviewActivity;

import dagger.Subcomponent;

/**
 * Created by Robert Zagórski on 2016-07-22.
 */
@ActivityScope
@Subcomponent(
        modules = PreviewActivityModule.class
)
public interface PreviewActivityComponent {

    PreviewActivity inject(PreviewActivity previewActivity);
}
