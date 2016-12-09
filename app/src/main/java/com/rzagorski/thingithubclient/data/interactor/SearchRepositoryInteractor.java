package com.rzagorski.thingithubclient.data.interactor;

import com.rzagorski.thingithubclient.data.api.ApiManager;
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

    public SearchRepositoryInteractor(ApiManager apiManager) {
        mApiManager = apiManager;
    }

    public Observable<List<GithubRepository>> build(String query) {
        this.mQuery = query;
        return build();
    }

    @Override
    public Observable<List<GithubRepository>> build() {
        return mApiManager.getRepositoriesBySearchQuery(mQuery)
                .map(new Func1<ApiSearchRepository, List<GithubRepository>>() {
                    @Override
                    public List<GithubRepository> call(ApiSearchRepository apiSearchRepository) {
                        return apiSearchRepository.getItems();
                    }
                });
    }
}
