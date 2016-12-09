package com.rzagorski.thingithubclient.utils.observable;

import android.util.Pair;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by Robert Zagórski on 2016-02-04.
 */
public class ObservableUtils {

    public static <T> Observable.Transformer<T, T> applySchedulers() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T, V> Observable<V> zip(Observable<T> observable, V v) {
        return observable.zipWith(Observable.just(v), new Func2<T, V, V>() {
            @Override
            public V call(T t, V v) {
                return v;
            }
        });
    }

    public static <T, V> Observable<Pair<T, V>> pair(T t, V v) {
        return pair(Observable.just(t), Observable.just(v));
    }

    public static <T, V> Observable<Pair<T, V>> pair(Observable<T> observable, V v) {
        return pair(observable, Observable.just(v));
    }

    public static <T, V> Observable<Pair<T, V>> pair(Observable<T> tObs, Observable<V> vObs) {
        return tObs.zipWith(vObs, new Func2<T, V, Pair<T, V>>() {
            @Override
            public Pair<T, V> call(T t, V v) {
                return new Pair<>(t, v);
            }
        });
    }
}