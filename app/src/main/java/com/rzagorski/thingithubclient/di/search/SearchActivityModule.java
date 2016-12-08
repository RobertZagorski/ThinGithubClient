package com.rzagorski.thingithubclient.di.search;

import com.rzagorski.thingithubclient.di.ActivityScope;
import com.rzagorski.thingithubclient.view.search.SearchActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Robert Zagórski on 2016-07-22.
 */
@Module
public class SearchActivityModule {
    private SearchActivity mSearchActivity;

    public SearchActivityModule(SearchActivity searchActivity) {
        this.mSearchActivity = searchActivity;
    }

    @Provides
    @ActivityScope
    SearchActivity provideSearchActivity() {
        return mSearchActivity;
    }
}
