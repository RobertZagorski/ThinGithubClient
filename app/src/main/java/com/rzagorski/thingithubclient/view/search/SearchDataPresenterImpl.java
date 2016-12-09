package com.rzagorski.thingithubclient.view.search;

import com.rzagorski.thingithubclient.data.interactor.SearchUserInteractor;
import com.rzagorski.thingithubclient.utils.abstracts.BasePresenter;

/**
 * Created by Robert Zagórski on 2016-12-08.
 */

public class SearchDataPresenterImpl extends BasePresenter<SearchData.View> implements SearchData.Presenter {

    SearchUserInteractor mSearchUserInteractor;

    public SearchDataPresenterImpl(SearchData.View view,
                                   SearchUserInteractor searchUserInteractor) {
        super(view);
        this.mSearchUserInteractor = searchUserInteractor;
    }

    @Override
    public void onSearchQuery(String query) {

    }
}
