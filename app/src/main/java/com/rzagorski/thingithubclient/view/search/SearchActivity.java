package com.rzagorski.thingithubclient.view.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.rzagorski.thingithubclient.R;
import com.rzagorski.thingithubclient.ThinGithubClientApplication;
import com.rzagorski.thingithubclient.di.search.SearchActivityComponent;
import com.rzagorski.thingithubclient.di.search.SearchActivityModule;
import com.rzagorski.thingithubclient.utils.FragmentHelper;
import com.rzagorski.thingithubclient.utils.interfaces.ComponentCreator;

import javax.inject.Inject;

public class SearchActivity extends AppCompatActivity
        implements ComponentCreator<SearchActivityComponent> {

    @Inject ListFragment listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getComponent().inject(this);
        getComponent().inject(listFragment);
        FragmentHelper.replaceFragment(getSupportFragmentManager(), listFragment, R.id.fragment_list);
    }

    @Override
    public SearchActivityComponent getComponent() {
        return ((ThinGithubClientApplication) getApplicationContext())
                .getApplicationComponent()
                .provide(new SearchActivityModule(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
