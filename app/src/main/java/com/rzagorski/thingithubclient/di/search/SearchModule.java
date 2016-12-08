package com.rzagorski.thingithubclient.di.search;

import com.rzagorski.thingithubclient.di.ActivityScope;
import com.rzagorski.thingithubclient.view.search.Search;
import com.rzagorski.thingithubclient.view.search.SearchPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Robert Zagórski on 2016-12-08.
 */

@Module
public class SearchModule {

    @Provides
    @ActivityScope
    Search.Presenter provideSearchPresenter(Search.View view) {
        return new SearchPresenterImpl(view);
    }
}
