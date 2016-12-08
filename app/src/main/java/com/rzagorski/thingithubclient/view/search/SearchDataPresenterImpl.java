package com.rzagorski.thingithubclient.view.search;

import com.rzagorski.thingithubclient.utils.abstracts.BasePresenter;

/**
 * Created by Robert Zagórski on 2016-12-08.
 */

public class SearchDataPresenterImpl extends BasePresenter<SearchData.View> implements SearchData.Presenter {

    public SearchDataPresenterImpl(SearchData.View view) {
        super(view);
    }

    @Override
    public void onSearchQuery(String query) {

    }
}
