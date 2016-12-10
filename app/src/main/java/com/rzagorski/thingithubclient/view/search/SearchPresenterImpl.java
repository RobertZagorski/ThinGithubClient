package com.rzagorski.thingithubclient.view.search;

import com.rzagorski.thingithubclient.utils.abstracts.BasePresenter;
import com.rzagorski.thingithubclient.utils.observable.DefaultSubscriber;

import java.util.concurrent.TimeUnit;

import javax.inject.Provider;

import rx.Subscriber;
import rx.functions.Func1;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

/**
 * Created by Robert Zagórski on 2016-12-08.
 */

public class SearchPresenterImpl extends BasePresenter<Search.View> implements Search.Presenter {

    SearchData.Presenter mSearchDataPresenter;

    private Subject<String, String> filteringSubject;
    Subscriber<String> filteringSubscriber;

    public SearchPresenterImpl(Provider<Search.View> viewProvider, SearchData.Presenter searchDataPresenter) {
        super(viewProvider);
        this.mSearchDataPresenter = searchDataPresenter;
    }

    @Override
    public void onQueryChanged(String query) {
        if (query == null || query.length() == 0) {
            return;
        }
        filteringSubject.onNext(query);
    }

    @Override
    public void onSearchOpened() {
        filteringSubject = PublishSubject.create();
        filteringSubscriber = new DefaultSubscriber<>();
        filteringSubject
                .throttleWithTimeout(350, TimeUnit.MILLISECONDS)
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String query) {
                        mSearchDataPresenter.onSearchQuery(query);
                        return query;
                    }
                }).subscribe(filteringSubscriber);
    }

    @Override
    public void onSearchClosed() {
        filteringSubscriber.unsubscribe();
        filteringSubject = null;
    }
}
