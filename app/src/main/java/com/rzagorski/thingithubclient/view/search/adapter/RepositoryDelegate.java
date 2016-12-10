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
import com.rzagorski.thingithubclient.model.app.GithubRepository;

import java.util.List;

/**
 * Created by Robert Zagórski on 2016-12-09.
 */

public class RepositoryDelegate extends AdapterDelegate<List<GithubItem>> {

    public RepositoryDelegate() {
    }

    @Override
    public boolean isForViewType(@NonNull List<GithubItem> items, int position) {
        return items.get(position) instanceof GithubRepository;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new RepositoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout_repository, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull List<GithubItem> items, int position,
                                 @NonNull RecyclerView.ViewHolder holder,
                                 @Nullable List<Object> payloads) {

        RepositoryViewHolder vh = (RepositoryViewHolder) holder;
        GithubRepository repository = (GithubRepository) items.get(position);

        vh.name.setText(repository.getName());
        if (repository.getDescription() != null) {
            vh.description.setText(repository.getDescription().toString());
        }
        vh.language.setText(repository.getLanguage());
        vh.starsCount.setText(String.valueOf(repository.getStargazersCount()));
        vh.forksCount.setText(String.valueOf(repository.getForksCount()));
    }

    static class RepositoryViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView description;
        public TextView starsCount;
        public TextView language;
        public TextView forksCount;

        public RepositoryViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            description = (TextView) itemView.findViewById(R.id.description);
            language = (TextView) itemView.findViewById(R.id.language);
            starsCount = (TextView) itemView.findViewById(R.id.stars_count);
            forksCount = (TextView) itemView.findViewById(R.id.forks_count);
        }
    }
}
