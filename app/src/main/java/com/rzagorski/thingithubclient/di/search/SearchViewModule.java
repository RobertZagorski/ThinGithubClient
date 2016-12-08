package com.rzagorski.thingithubclient.di.search;

import com.rzagorski.thingithubclient.view.search.Search;
import com.rzagorski.thingithubclient.view.search.SearchInput;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Robert Zagórski on 2016-12-08.
 */
@Module
public abstract class SearchViewModule {

    @Binds
    public abstract Search.View provideSearchView(SearchInput view);
}
