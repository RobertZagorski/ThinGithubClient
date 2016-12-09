package com.rzagorski.thingithubclient.view.search;

import com.rzagorski.thingithubclient.utils.abstracts.BasePresenter;

/**
 * Created by Robert Zagórski on 2016-12-08.
 */

public class SearchPresenterImpl extends BasePresenter<Search.View> implements Search.Presenter {

    SearchData.Presenter mSearchDataPresenter;

    public SearchPresenterImpl(Search.View view, SearchData.Presenter searchDataPresenter) {
        super(view);
        this.mSearchDataPresenter = searchDataPresenter;
    }

    @Override
    public void onQueryChanged(String query) {
        mSearchDataPresenter.onSearchQuery(query);
    }
}
