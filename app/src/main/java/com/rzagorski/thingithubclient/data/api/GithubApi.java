package com.rzagorski.thingithubclient.data.api;

import com.rzagorski.thingithubclient.model.api.ApiSearchRepository;
import com.rzagorski.thingithubclient.model.api.ApiSearchUser;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Robert Zagórski on 2016-12-09.
 */

public interface GithubApi {
    public static final String BASE_URL = "api.github.com";

    @GET("/search/users")
    Observable<ApiSearchUser> getSearchUser(@Query("q") String query);

    @GET("/search/repositories")
    Observable<ApiSearchRepository> getSearchRepository(@Query("q") String query);
}
