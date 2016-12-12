package com.rzagorski.thingithubclient.view.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.rzagorski.thingithubclient.R;
import com.rzagorski.thingithubclient.di.search.SearchActivityComponent;
import com.rzagorski.thingithubclient.model.app.GithubItem;
import com.rzagorski.thingithubclient.model.app.GithubUser;
import com.rzagorski.thingithubclient.utils.interfaces.ComponentCreator;
import com.rzagorski.thingithubclient.view.preview.PreviewActivity;
import com.rzagorski.thingithubclient.view.search.adapter.SearchAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.Lazy;

/**
 * Created by Robert Zagórski on 2016-12-09.
 */

public class ListFragment extends Fragment implements SearchData.View {

    @Inject Lazy<SearchData.Presenter> mPresenterProvider;
    @Inject SearchAdapter searchAdapter;

    @BindView(R.id.search_status) TextView searchStatus;
    @BindView(R.id.progress_bar) ProgressBar progressBar;
    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;

    private Unbinder butterknifeUnbinder;

    private boolean loading = false;
    int pastVisiblesItems, totalItemCount;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        if (savedInstanceState != null) {
            ((ComponentCreator<SearchActivityComponent>)getActivity())
                    .getComponent()
                    .inject(this);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        butterknifeUnbinder = ButterKnife.bind(this, view);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                mRecyclerView.getContext(),
                layoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        mRecyclerView.setAdapter(searchAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy <= 0) {
                    return;
                }
                totalItemCount = recyclerView.getLayoutManager().getItemCount();
                pastVisiblesItems = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();

                if (!loading) {
                    if (pastVisiblesItems == totalItemCount - 1) {
                        loading = true;
                        mPresenterProvider.get().onListEnd();
                    }
                }
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        butterknifeUnbinder.unbind();
    }

    @Override
    public void showLoading() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (searchAdapter.getItemCount() == 0) {
                    searchStatus.setVisibility(View.VISIBLE);
                    searchStatus.setText(getString(R.string.search_waiting));
                }
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void hideLoading() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.INVISIBLE);
                searchStatus.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void onSearchResults(List<GithubItem> githubItemList) {
        loading = false;
        searchAdapter.addAllItems(githubItemList);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                searchAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onSearchResultsError(Throwable e) {
        //TODO show error
    }

    @Override
    public void clearResults() {
        searchAdapter.clear();
    }

    @Override
    public void onUserClick(int adapterPosition) {
        GithubUser githubUser = (GithubUser) searchAdapter.getItemAt(adapterPosition);
        mPresenterProvider.get().onUserClick(githubUser);
    }

    @Override
    public void showUserPreview() {
        Intent intent = new Intent(getActivity(), PreviewActivity.class);
        startActivity(intent);
    }
}
