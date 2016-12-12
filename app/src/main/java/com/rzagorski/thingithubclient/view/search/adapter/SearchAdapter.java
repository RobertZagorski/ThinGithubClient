package com.rzagorski.thingithubclient.view.search.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;
import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager;
import com.rzagorski.thingithubclient.model.app.GithubItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Robert Zagórski on 2016-12-09.
 */

public class SearchAdapter extends RecyclerView.Adapter {

    private AdapterDelegatesManager<List<GithubItem>> mDelegatesManager;
    private List<GithubItem> mItems;

    public SearchAdapter(Set<? extends AdapterDelegate<List<GithubItem>>> delegatesSet) {
        mItems = new ArrayList<>();
        mDelegatesManager = new AdapterDelegatesManager<>();
        for (AdapterDelegate<List<GithubItem>> adapterDelegate : delegatesSet) {
            mDelegatesManager.addDelegate(adapterDelegate);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mDelegatesManager.getItemViewType(mItems, position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return mDelegatesManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        mDelegatesManager.onBindViewHolder(mItems, position, holder);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public GithubItem getItemAt(int position) {
        return mItems.get(position);
    }

    public void addAllItems(List<GithubItem> list) {
        mItems.addAll(list);
    }

    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }
}
