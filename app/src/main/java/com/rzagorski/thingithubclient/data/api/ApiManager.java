package com.rzagorski.thingithubclient.data.api;

import android.support.annotation.Nullable;

import com.rzagorski.thingithubclient.model.api.ApiSearchRepository;
import com.rzagorski.thingithubclient.model.api.ApiSearchUser;

import rx.Observable;

/**
 * Created by Robert Zagórski on 2016-12-09.
 */

public interface ApiManager {

    Observable<ApiSearchUser> getUsersBySearchQuery(String query, @Nullable Integer page);

    Observable<ApiSearchRepository> getRepositoriesBySearchQuery(String query, @Nullable Integer page);
}
