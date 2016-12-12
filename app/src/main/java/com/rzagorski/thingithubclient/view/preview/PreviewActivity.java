package com.rzagorski.thingithubclient.view.preview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.rzagorski.thingithubclient.R;
import com.rzagorski.thingithubclient.ThinGithubClientApplication;
import com.rzagorski.thingithubclient.di.preview.PreviewActivityComponent;
import com.rzagorski.thingithubclient.di.preview.PreviewActivityModule;
import com.rzagorski.thingithubclient.utils.interfaces.ComponentCreator;

public class PreviewActivity extends AppCompatActivity implements ComponentCreator<PreviewActivityComponent> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
    }

    @Override
    public PreviewActivityComponent getComponent() {
        return ((ThinGithubClientApplication) getApplicationContext())
                .getPreviewComponent()
                .provide(new PreviewActivityModule(this));
    }
}
