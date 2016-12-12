package com.rzagorski.thingithubclient.view.preview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rzagorski.thingithubclient.R;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.Lazy;

/**
 * Created by Robert Zagórski on 2016-12-12.
 */

public class PreviewFragment extends Fragment implements Preview.View {

    @Inject Lazy<Preview.Presenter> mPresenter;

    Unbinder butterknifeUnbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.preview_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onStop() {
        super.onStop();
        butterknifeUnbinder.unbind();
    }
}
