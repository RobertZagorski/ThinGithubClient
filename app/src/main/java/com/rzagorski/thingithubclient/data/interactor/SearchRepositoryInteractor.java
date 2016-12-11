package com.rzagorski.thingithubclient.data.interactor;

import com.rzagorski.thingithubclient.data.api.ApiManager;
import com.rzagorski.thingithubclient.data.api.GithubApi;
import com.rzagorski.thingithubclient.model.api.ApiSearchRepository;
import com.rzagorski.thingithubclient.model.app.GithubRepository;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Robert Zagórski on 2016-12-09.
 */

public class SearchRepositoryInteractor implements Interactor<List<GithubRepository>> {
    private ApiManager mApiManager;
    private String mQuery;
    private Integer mMaxItems;
    private Integer mLastInvokedPage = 0;

    public SearchRepositoryInteractor(ApiManager apiManager) {
        mApiManager = apiManager;
    }

    public Observable<List<GithubRepository>> build(String query) {
        this.mQuery = query;
        return build();
    }

    @Override
    public Observable<List<GithubRepository>> build() {
        return mApiManager.getRepositoriesBySearchQuery(mQuery, mLastInvokedPage)
                .map(new Func1<ApiSearchRepository, List<GithubRepository>>() {
                    @Override
                    public List<GithubRepository> call(ApiSearchRepository apiSearchRepository) {
                        mMaxItems = apiSearchRepository.getTotalCount();
                        return apiSearchRepository.getItems();
                    }
                });
    }

    public Observable<List<GithubRepository>> getNextPage() {
        mLastInvokedPage++;
        return build();
    }

    public void clearCache() {
        mQuery = null;
        mLastInvokedPage = 0;
    }

    public boolean isMore() {
        if (mMaxItems == null) {
            return false;
        }
        return mLastInvokedPage * GithubApi.ITEMS_PER_PAGE < mMaxItems - GithubApi.ITEMS_PER_PAGE;
    }
}
