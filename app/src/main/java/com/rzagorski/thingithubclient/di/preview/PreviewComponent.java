package com.rzagorski.thingithubclient.di.preview;


import dagger.Subcomponent;

/**
 * Created by Robert Zagórski on 07.06.2016.
 * Provides activities inside configuration component.
 */
@PreviewScope
@Subcomponent(
        modules = PreviewModule.class
)
public interface PreviewComponent {

    PreviewActivityComponent provide(PreviewActivityModule previewActivityModule);
}