package com.rzagorski.thingithubclient.model.app;

import java.util.Comparator;

/**
 * Created by Robert Zagórski on 2016-12-09.
 */

public class GithubItemComparator implements Comparator<GithubItem> {
    @Override
    public int compare(GithubItem item1, GithubItem item2) {
        return item1.getId().compareTo(item2.getId());
    }
}
