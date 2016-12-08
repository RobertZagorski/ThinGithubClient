package com.rzagorski.thingithubclient.di;

import com.rzagorski.thingithubclient.di.search.SearchActivityComponent;
import com.rzagorski.thingithubclient.di.search.SearchActivityModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                ApplicationModule.class,
                DataModule.class
        }
)
public interface ApplicationComponent {

    SearchActivityComponent provide(SearchActivityModule searchActivityModule);
}
