package com.rzagorski.thingithubclient.view.search.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;
import com.rzagorski.thingithubclient.R;
import com.rzagorski.thingithubclient.model.app.GithubItem;
import com.rzagorski.thingithubclient.model.app.GithubUser;

import java.util.List;

/**
 * Created by Robert Zagórski on 2016-12-09.
 */

public class UserDelegate extends AdapterDelegate<List<GithubItem>> {

    public UserDelegate() {
    }

    @Override
    public boolean isForViewType(@NonNull List<GithubItem> items, int position) {
        return items.get(position) instanceof GithubUser;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new UserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout_user, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull List<GithubItem> items, int position,
                                 @NonNull RecyclerView.ViewHolder holder,
                                 @Nullable List<Object> payloads) {

        UserViewHolder vh = (UserViewHolder) holder;
        GithubUser user = (GithubUser) items.get(position);

        vh.name.setText(user.getLogin());
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {

        public TextView name;

        public UserViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
        }
    }
}
