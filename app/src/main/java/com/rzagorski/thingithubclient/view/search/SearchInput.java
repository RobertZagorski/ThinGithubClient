package com.rzagorski.thingithubclient.view.search;

import android.view.MenuItem;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import javax.inject.Inject;

/**
 * Created by Robert Zagórski on 2016-12-08.
 */

public class SearchInput implements Search.View, MaterialSearchView.OnQueryTextListener, MaterialSearchView.SearchViewListener {

    private MaterialSearchView mMaterialSearchView;
    @Inject Search.Presenter mPresenter;

    public void init(MaterialSearchView materialSearchView) {
        this.mMaterialSearchView = materialSearchView;
        mMaterialSearchView.setOnQueryTextListener(this);
        mMaterialSearchView.setOnSearchViewListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        mPresenter.onQueryChanged(newText);
        return true;
    }

    @Override
    public void onSearchViewShown() {
        mPresenter.onSearchOpened();
    }

    @Override
    public void onSearchViewClosed() {
        mPresenter.onSearchClosed();
    }

    public void setMenuItem(MenuItem menuItem) {
        this.mMaterialSearchView.setMenuItem(menuItem);
    }


    public boolean isSearchOpen() {
        return mMaterialSearchView.isSearchOpen();
    }

    public void closeSearch() {
        mMaterialSearchView.closeSearch();
    }
}
