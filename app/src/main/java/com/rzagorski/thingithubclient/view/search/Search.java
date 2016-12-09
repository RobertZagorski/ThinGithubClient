package com.rzagorski.thingithubclient.view.search;

import com.rzagorski.thingithubclient.utils.abstracts.MvpView;

/**
 * Created by Robert Zagórski on 2016-12-08.
 */

public class Search {

    public interface Presenter {
        void onQueryChanged(String query);
    }

    public interface View extends MvpView {

    }
}
