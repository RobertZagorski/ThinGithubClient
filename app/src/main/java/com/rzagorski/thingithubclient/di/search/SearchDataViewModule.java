package com.rzagorski.thingithubclient.di.search;

import com.rzagorski.thingithubclient.view.search.ListFragment;
import com.rzagorski.thingithubclient.view.search.SearchData;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Robert Zagórski on 2016-12-08.
 */
@Module
public abstract class SearchDataViewModule {

    @Binds
    public abstract SearchData.View provideSearchView(ListFragment view);
}
