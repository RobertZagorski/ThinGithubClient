package com.rzagorski.thingithubclient.data.interactor;


import rx.Observable;

/**
 * Created by Robert Zagórski on 2016-07-22.
 */
public interface Interactor<T> {

    Observable<T> build();
}
