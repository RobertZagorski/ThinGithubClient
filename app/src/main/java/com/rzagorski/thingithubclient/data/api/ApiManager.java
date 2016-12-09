package com.rzagorski.thingithubclient.data.api;

import com.rzagorski.thingithubclient.model.api.ApiSearchUser;

import rx.Observable;

/**
 * Created by Robert Zagórski on 2016-12-09.
 */

public interface ApiManager {

    Observable<ApiSearchUser> getUsersBySearchQuery(String query);
}
