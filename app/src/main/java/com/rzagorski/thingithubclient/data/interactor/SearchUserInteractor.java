package com.rzagorski.thingithubclient.data.interactor;

import com.rzagorski.thingithubclient.data.api.ApiManager;
import com.rzagorski.thingithubclient.data.api.GithubApi;
import com.rzagorski.thingithubclient.model.api.ApiSearchUser;
import com.rzagorski.thingithubclient.model.app.GithubUser;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Robert Zagórski on 2016-12-09.
 */

public class SearchUserInteractor implements Interactor<List<GithubUser>> {
    private ApiManager mApiManager;
    private String mQuery;
    private Integer mMaxItems;
    private Integer mLastInvokedPage = 0;

    public SearchUserInteractor(ApiManager apiManager) {
        mApiManager = apiManager;
    }

    public Observable<List<GithubUser>> build(String query) {
        this.mQuery = query;
        return build();
    }

    @Override
    public Observable<List<GithubUser>> build() {
        return mApiManager.getUsersBySearchQuery(mQuery, mLastInvokedPage)
                .map(new Func1<ApiSearchUser, List<GithubUser>>() {
                    @Override
                    public List<GithubUser> call(ApiSearchUser apiSearchUser) {
                        mMaxItems = apiSearchUser.getTotalCount();
                        return apiSearchUser.getItems();
                    }
                });
    }

    public Observable<List<GithubUser>> getNextPage() {
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
