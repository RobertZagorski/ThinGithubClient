package com.rzagorski.thingithubclient.view.preview;

import com.rzagorski.thingithubclient.utils.abstracts.BasePresenter;

import dagger.Lazy;

/**
 * Created by Robert Zagórski on 2016-12-12.
 */

public class PreviewPresenterImpl extends BasePresenter<Preview.View> implements Preview.Presenter {

    public PreviewPresenterImpl(Lazy<Preview.View> view) {
        super(view);
    }
}
