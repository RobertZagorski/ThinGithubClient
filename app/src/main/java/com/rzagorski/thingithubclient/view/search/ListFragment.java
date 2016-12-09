package com.rzagorski.thingithubclient.view.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rzagorski.thingithubclient.R;
import com.rzagorski.thingithubclient.model.app.GithubItem;
import com.rzagorski.thingithubclient.view.search.adapter.SearchAdapter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Robert Zagórski on 2016-12-09.
 */

public class ListFragment extends Fragment implements SearchData.View {

    @Inject SearchData.Presenter mPresenter;
    RecyclerView mRecyclerView;
    SearchAdapter searchAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        searchAdapter = new SearchAdapter();
        mRecyclerView.setAdapter(searchAdapter);
    }

    @Override
    public void onSearchResults(List<GithubItem> githubItemList) {
        searchAdapter.addAllItems(githubItemList);
        searchAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSearchResultsError(Throwable e) {
        //TODO show error
    }
}
