package com.rzagorski.thingithubclient.view.search;

import com.rzagorski.thingithubclient.data.interactor.SearchRepositoryInteractor;
import com.rzagorski.thingithubclient.data.interactor.SearchUserInteractor;
import com.rzagorski.thingithubclient.utils.abstracts.BasePresenter;

/**
 * Created by Robert Zagórski on 2016-12-08.
 */

public class SearchDataPresenterImpl extends BasePresenter<SearchData.View> implements SearchData.Presenter {

    SearchUserInteractor mSearchUserInteractor;
    SearchRepositoryInteractor mSearchRepositoryInteractor;

    public SearchDataPresenterImpl(SearchData.View view,
                                   SearchUserInteractor searchUserInteractor,
                                   SearchRepositoryInteractor searchRepositoryInteractor) {
        super(view);
        this.mSearchUserInteractor = searchUserInteractor;
        this.mSearchRepositoryInteractor = searchRepositoryInteractor;
    }

    @Override
    public void onSearchQuery(String query) {

    }
}
