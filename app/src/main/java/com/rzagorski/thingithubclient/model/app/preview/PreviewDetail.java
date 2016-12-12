package com.rzagorski.thingithubclient.model.app.preview;

import android.support.annotation.StringRes;

/**
 * Created by Robert Zagórski on 2016-12-12.
 */

public class PreviewDetail {
    @StringRes int titleId;
    String text;

    public PreviewDetail(int titleId, String text) {
        this.titleId = titleId;
        this.text = text;
    }

    public int getTitleId() {
        return titleId;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
