package com.rzagorski.thingithubclient.di.search;

import com.rzagorski.thingithubclient.data.api.ApiManager;
import com.rzagorski.thingithubclient.data.interactor.SearchRepositoryInteractor;
import com.rzagorski.thingithubclient.data.interactor.SearchUserInteractor;
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
    SearchData.Presenter provideSearchPresenter(SearchData.View view,
                                                SearchUserInteractor searchUserInteractor,
                                                SearchRepositoryInteractor searchRepositoryInteractor) {
        return new SearchDataPresenterImpl(view, searchUserInteractor, searchRepositoryInteractor);
    }
}
