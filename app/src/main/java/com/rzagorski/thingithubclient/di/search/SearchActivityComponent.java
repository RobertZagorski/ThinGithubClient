package com.rzagorski.thingithubclient.di.search;

import com.rzagorski.thingithubclient.di.ActivityScope;
import com.rzagorski.thingithubclient.view.search.ListFragment;
import com.rzagorski.thingithubclient.view.search.SearchActivity;
import com.rzagorski.thingithubclient.view.search.SearchInput;

import dagger.Subcomponent;

/**
 * Created by Robert Zagórski on 2016-07-22.
 */
@ActivityScope
@Subcomponent(
        modules = {
                SearchActivityModule.class,
                SearchModule.class,
                SearchViewModule.class,
                SearchDataModule.class,
                SearchDataViewModule.class
        }

)
public interface SearchActivityComponent {

    SearchActivity inject(SearchActivity searchActivity);

    ListFragment inject(ListFragment listFragment);

    SearchInput inject(SearchInput searchInput);
}
