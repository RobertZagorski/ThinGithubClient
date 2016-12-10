package com.rzagorski.thingithubclient.di.search;

import com.rzagorski.thingithubclient.view.search.ListFragment;
import com.rzagorski.thingithubclient.view.search.Search;
import com.rzagorski.thingithubclient.view.search.SearchData;
import com.rzagorski.thingithubclient.view.search.SearchDataPresenterImpl;
import com.rzagorski.thingithubclient.view.search.SearchInput;
import com.rzagorski.thingithubclient.view.search.SearchPresenterImpl;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Robert Zagórski on 2016-12-08.
 */
@Module
public abstract class SearchViewModule {

    @Binds
    public abstract SearchData.View provideSearchDataView(ListFragment view);

    @Binds
    public abstract SearchData.Presenter provideSearchDataPresenter(SearchDataPresenterImpl presenter);

    @Binds
    public abstract Search.View provideSearchView(SearchInput view);

    @Binds
    abstract Search.Presenter provideSearchPresenter(SearchPresenterImpl presenter);
}
