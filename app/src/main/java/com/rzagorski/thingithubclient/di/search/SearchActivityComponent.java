package com.rzagorski.thingithubclient.di.search;

import com.rzagorski.thingithubclient.di.ActivityScope;
import com.rzagorski.thingithubclient.view.search.SearchActivity;

import dagger.Subcomponent;

/**
 * Created by Robert Zagórski on 2016-07-22.
 */
@ActivityScope
@Subcomponent(
        modules = SearchActivityModule.class
)
public interface SearchActivityComponent {

    SearchActivity inject(SearchActivity searchActivity);
}
