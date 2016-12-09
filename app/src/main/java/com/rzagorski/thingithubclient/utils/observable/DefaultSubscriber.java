package com.rzagorski.thingithubclient.utils.observable;

import rx.Subscriber;

/**
 * Created by Robert Zagórski on 2016-02-09.
 */
public class DefaultSubscriber<T> extends Subscriber<T> {
    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
    }

    @Override
    public void onNext(T t) {
    }
}
