package com.rzagorski.thingithubclient.di.search;

import com.rzagorski.thingithubclient.data.api.ApiManager;
import com.rzagorski.thingithubclient.data.interactor.SearchRepositoryInteractor;
import com.rzagorski.thingithubclient.data.interactor.SearchUserInteractor;
import com.rzagorski.thingithubclient.di.ActivityScope;
import com.rzagorski.thingithubclient.view.search.ListFragment;
import com.rzagorski.thingithubclient.view.search.Search;
import com.rzagorski.thingithubclient.view.search.SearchActivity;
import com.rzagorski.thingithubclient.view.search.SearchData;
import com.rzagorski.thingithubclient.view.search.SearchDataPresenterImpl;
import com.rzagorski.thingithubclient.view.search.SearchInput;
import com.rzagorski.thingithubclient.view.search.SearchPresenterImpl;

import dagger.Lazy;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Robert Zagórski on 2016-07-22.
 */
@Module(
        includes = SearchViewModule.class
)
public class SearchActivityModule {
    private SearchActivity mSearchActivity;
    private static SearchDataPresenterImpl searchDataPresenter;

    public SearchActivityModule(SearchActivity searchActivity) {
        this.mSearchActivity = searchActivity;
    }

    @Provides
    @ActivityScope
    SearchActivity provideSearchActivity() {
        return mSearchActivity;
    }

    @Provides
    @ActivityScope
    ListFragment provideListFragment() {
        return new ListFragment();
    }

    @Provides
    @ActivityScope
    SearchInput provideSearchInput(Lazy<Search.Presenter> presenterProvider) {
        return new SearchInput(presenterProvider);
    }

    @Provides
    @ActivityScope
    SearchUserInteractor provideSearchUserInteractor(ApiManager apiManager) {
        return new SearchUserInteractor(apiManager);
    }

    @Provides
    @ActivityScope
    SearchRepositoryInteractor provideSearchRepositoryInteractor(ApiManager apiManager) {
        return new SearchRepositoryInteractor(apiManager);
    }

    @Provides
    @ActivityScope
    SearchDataPresenterImpl provideSearchDataPresenter(Lazy<SearchData.View> viewProvider,
                                                       SearchUserInteractor searchUserInteractor,
                                                       SearchRepositoryInteractor searchRepositoryInteractor) {
        if (searchDataPresenter == null) {
            searchDataPresenter = new SearchDataPresenterImpl(viewProvider,
                    searchUserInteractor,
                    searchRepositoryInteractor);
        }
        return searchDataPresenter;
    }

    @Provides
    @ActivityScope
    SearchPresenterImpl provideSearchPresenter(Lazy<Search.View> viewProvider,
                                               SearchData.Presenter searchDataPresenter) {
        return new SearchPresenterImpl(viewProvider, searchDataPresenter);
    }
}
