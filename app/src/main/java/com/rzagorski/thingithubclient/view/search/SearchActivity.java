package com.rzagorski.thingithubclient.view.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
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
    @Inject SearchInput searchInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getComponent().inject(this);
        getComponent().inject(searchInput);
        initViews();
        FragmentHelper.replaceFragment(getSupportFragmentManager(), listFragment, R.id.fragment_list);
    }

    private void initViews() {
        MaterialSearchView materialSearchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchInput.init(materialSearchView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public SearchActivityComponent getComponent() {
        return ((ThinGithubClientApplication) getApplicationContext())
                .getApplicationComponent()
                .provide(new SearchActivityModule(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_activity, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchInput.setMenuItem(item);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (searchInput.isSearchOpen()) {
            searchInput.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
