package com.rzagorski.thingithubclient.view.search;

import com.rzagorski.thingithubclient.utils.abstracts.BasePresenter;
import com.rzagorski.thingithubclient.utils.observable.DefaultSubscriber;

import java.util.concurrent.TimeUnit;

import dagger.Lazy;
import rx.Subscriber;
import rx.subjects.PublishSubject;

/**
 * Created by Robert Zagórski on 2016-12-08.
 */

public class SearchPresenterImpl extends BasePresenter<Search.View> implements Search.Presenter {

    SearchData.Presenter mSearchDataPresenter;

    private PublishSubject<String> filteringSubject;
    Subscriber<String> filteringSubscriber;

    public SearchPresenterImpl(Lazy<Search.View> viewProvider, SearchData.Presenter searchDataPresenterProvider) {
        super(viewProvider);
        this.mSearchDataPresenter = searchDataPresenterProvider;
    }

    @Override
    public void onQueryChanged(String query) {
        if (query == null || query.length() == 0) {
            mSearchDataPresenter.onCancelSearch();
            return;
        }
        filteringSubject.onNext(query);
    }

    @Override
    public void onSearchOpened() {
        filteringSubject = PublishSubject.create();
        filteringSubscriber = new FilteringSubscriber();
        filteringSubject
                .throttleWithTimeout(350, TimeUnit.MILLISECONDS)
                .subscribe(filteringSubscriber);
    }

    @Override
    public void onSearchClosed() {
        if (!filteringSubscriber.isUnsubscribed()) {
            filteringSubscriber.unsubscribe();
        }
        filteringSubject = null;
    }

    private class FilteringSubscriber extends DefaultSubscriber<String> {
        @Override
        public void onCompleted() {
            super.onCompleted();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onNext(String query) {
            mSearchDataPresenter.onSearchQuery(query);
        }
    }
}
