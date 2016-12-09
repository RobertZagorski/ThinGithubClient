package com.rzagorski.thingithubclient.data.api.retrofit;

import com.rzagorski.thingithubclient.data.api.ApiManager;
import com.rzagorski.thingithubclient.model.api.ApiSearchRepository;
import com.rzagorski.thingithubclient.model.api.ApiSearchUser;

import rx.Observable;

/**
 * Created by Robert Zagórski on 2016-12-09.
 */

public class RetrofitApiManagerImpl implements ApiManager {


    @Override
    public Observable<ApiSearchUser> getUsersBySearchQuery(String query) {
        return null;
    }

    @Override
    public Observable<ApiSearchRepository> getRepositoriesBySearchQuery(String query) {
        return null;
    }
}
