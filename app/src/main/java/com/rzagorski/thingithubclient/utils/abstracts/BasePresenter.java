package com.rzagorski.thingithubclient.utils.abstracts;

import android.support.annotation.CallSuper;

import dagger.Lazy;
import rx.subscriptions.CompositeSubscription;

/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getView().
 */
public class BasePresenter<T extends MvpView> implements Presenter {

    private Lazy<T> mMvpViewProvider;
    private CompositeSubscription mSubscription;

    public BasePresenter(Lazy<T> view) {
        this.mMvpViewProvider = view;
        checkViewAttached();
        mSubscription = new CompositeSubscription();
    }

    @CallSuper
    @Override
    public void detachView() {
        if (mSubscription != null && mSubscription.isUnsubscribed()) {
            mSubscription.clear();
        }
        //mMvpViewProvider = null;
    }

    public boolean isViewAttached() {
        return mMvpViewProvider != null;
    }

    public T getView() {
        return mMvpViewProvider.get();
    }

    public CompositeSubscription getSubscription() {
        return mSubscription;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Controller.attachView(MvcView) before" +
                    " requesting data to the Controller");
        }
    }
}

