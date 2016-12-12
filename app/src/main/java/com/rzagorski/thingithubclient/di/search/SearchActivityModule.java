package com.rzagorski.thingithubclient.di.search;

import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;
import com.rzagorski.thingithubclient.data.ScopeManager;
import com.rzagorski.thingithubclient.data.api.ApiManager;
import com.rzagorski.thingithubclient.data.interactor.SearchRepositoryInteractor;
import com.rzagorski.thingithubclient.data.interactor.SearchUserInteractor;
import com.rzagorski.thingithubclient.di.ActivityScope;
import com.rzagorski.thingithubclient.model.app.GithubItem;
import com.rzagorski.thingithubclient.view.search.ListFragment;
import com.rzagorski.thingithubclient.view.search.Search;
import com.rzagorski.thingithubclient.view.search.SearchActivity;
import com.rzagorski.thingithubclient.view.search.SearchData;
import com.rzagorski.thingithubclient.view.search.SearchDataPresenterImpl;
import com.rzagorski.thingithubclient.view.search.SearchInput;
import com.rzagorski.thingithubclient.view.search.SearchPresenterImpl;
import com.rzagorski.thingithubclient.view.search.adapter.RepositoryDelegate;
import com.rzagorski.thingithubclient.view.search.adapter.SearchAdapter;
import com.rzagorski.thingithubclient.view.search.adapter.UserDelegate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private static ListFragment listFragment;
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
        if (listFragment == null) {
            listFragment = new ListFragment();
        }
        return listFragment;
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
                                                       SearchRepositoryInteractor searchRepositoryInteractor,
                                                       ScopeManager scopeManager) {
        if (searchDataPresenter == null) {
            searchDataPresenter = new SearchDataPresenterImpl(viewProvider,
                    searchUserInteractor,
                    searchRepositoryInteractor,
                    scopeManager);
        }
        return searchDataPresenter;
    }

    @Provides
    @ActivityScope
    SearchPresenterImpl provideSearchPresenter(Lazy<Search.View> viewProvider,
                                               SearchData.Presenter searchDataPresenter) {
        return new SearchPresenterImpl(viewProvider, searchDataPresenter);
    }

    @Provides
    @ActivityScope
    UserDelegate provideUserDelegate(SearchData.View view) {
        return new UserDelegate(view);
    }

    @Provides
    @ActivityScope
    RepositoryDelegate provideRepositoryDelegate() {
        return new RepositoryDelegate();
    }

    @Provides
    Set<? extends AdapterDelegate<List<GithubItem>>> provideAdapterDelegates(UserDelegate userDelegate, RepositoryDelegate repositoryDelegate) {
        return new HashSet<>(Arrays.asList(userDelegate, repositoryDelegate));
    }

    @Provides
    SearchAdapter provideSearchAdapter(Set<? extends AdapterDelegate<List<GithubItem>>> delegatesSet) {
        return new SearchAdapter(delegatesSet);
    }
}
