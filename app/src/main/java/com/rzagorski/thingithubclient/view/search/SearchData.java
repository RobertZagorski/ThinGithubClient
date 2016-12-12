package com.rzagorski.thingithubclient.view.search;

import com.rzagorski.thingithubclient.model.app.GithubItem;
import com.rzagorski.thingithubclient.utils.abstracts.MvpView;

import java.util.List;

/**
 * Created by Robert Zagórski on 2016-12-08.
 */

public class SearchData {

    public interface Presenter {

        void onSearchQuery(String query);

        void onCancelSearch();

        void onListEnd();
    }

    public interface View extends MvpView {

        void showLoading();

        void hideLoading();

        void onSearchResults(List<GithubItem> githubItemList);

        void onSearchResultsError(Throwable e);

        void clearResults();
    }
}
