package com.rzagorski.thingithubclient.view.search;

import com.rzagorski.thingithubclient.utils.abstracts.MvpView;

/**
 * Created by Robert Zagórski on 2016-12-08.
 */

public class SearchData {

    public interface Presenter {

        void onSearchQuery(String query);
    }

    public interface View extends MvpView {

    }
}
