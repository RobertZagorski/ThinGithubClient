package com.rzagorski.thingithubclient.view.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rzagorski.thingithubclient.R;
import com.rzagorski.thingithubclient.model.app.GithubItem;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Robert Zagórski on 2016-12-09.
 */

public class ListFragment extends Fragment implements SearchData.View {

    @Inject SearchData.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onSearchResults(List<GithubItem> githubItemList) {
        //TODO show results
    }

    @Override
    public void onSearchResultsError(Throwable e) {
        //TODO show error
    }
}
