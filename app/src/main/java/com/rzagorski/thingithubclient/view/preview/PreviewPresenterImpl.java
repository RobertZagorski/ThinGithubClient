package com.rzagorski.thingithubclient.view.preview;

import com.rzagorski.thingithubclient.model.app.GithubUser;
import com.rzagorski.thingithubclient.utils.abstracts.BasePresenter;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import dagger.Lazy;

/**
 * Created by Robert Zagórski on 2016-12-12.
 */

public class PreviewPresenterImpl extends BasePresenter<Preview.View> implements Preview.Presenter {
    GithubUser mGithubUser;

    public PreviewPresenterImpl(Lazy<Preview.View> view, GithubUser githubUser) {
        super(view);
        mGithubUser = githubUser;
    }

    public RequestCreator downloadAvatar(Picasso picasso) {
        return picasso.load(mGithubUser.getAvatarUrl()).fit().centerCrop();
    }

    public void getContent() {
        getView().showUsername(mGithubUser.getLogin());
    }
}
