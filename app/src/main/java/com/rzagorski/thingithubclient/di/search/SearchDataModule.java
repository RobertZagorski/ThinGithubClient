package com.rzagorski.thingithubclient.di.search;

import com.rzagorski.thingithubclient.di.ActivityScope;
import com.rzagorski.thingithubclient.view.search.SearchData;
import com.rzagorski.thingithubclient.view.search.SearchDataPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Robert Zagórski on 2016-12-08.
 */

@Module
public class SearchDataModule {

    @Provides
    @ActivityScope
    SearchData.Presenter provideSearchPresenter(SearchData.View view) {
        return new SearchDataPresenterImpl(view);
    }
}
