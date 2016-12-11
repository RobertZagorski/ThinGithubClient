package com.rzagorski.thingithubclient.data.api.retrofit;

import android.support.annotation.Nullable;

import com.rzagorski.thingithubclient.data.api.ApiManager;
import com.rzagorski.thingithubclient.data.api.GithubApi;
import com.rzagorski.thingithubclient.model.api.ApiSearchRepository;
import com.rzagorski.thingithubclient.model.api.ApiSearchUser;

import rx.Observable;

/**
 * Created by Robert Zagórski on 2016-12-09.
 */

public class RetrofitApiManagerImpl implements ApiManager {

    GithubApi mGithubApi;

    public RetrofitApiManagerImpl(GithubApi githubApi) {
        this.mGithubApi = githubApi;
    }

    @Override
    public Observable<ApiSearchUser> getUsersBySearchQuery(String query) {
        return mGithubApi.getSearchUser(query);
    }

    @Override
    public Observable<ApiSearchRepository> getRepositoriesBySearchQuery(String query,
                                                                        @Nullable Integer page) {
        return mGithubApi.getSearchRepository(query, page);
    }
}
