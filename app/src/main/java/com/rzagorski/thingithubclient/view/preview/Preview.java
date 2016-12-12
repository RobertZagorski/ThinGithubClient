package com.rzagorski.thingithubclient.view.preview;

import com.rzagorski.thingithubclient.utils.abstracts.MvpView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

/**
 * Created by Robert Zagórski on 2016-12-12.
 */

public class Preview {

    public interface View extends MvpView {

        void showUsername(String login);
    }

    public interface Presenter {
        RequestCreator downloadAvatar(Picasso picasso);

        void getContent();
    }
}
