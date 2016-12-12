package com.rzagorski.thingithubclient.view.search;

import com.rzagorski.thingithubclient.data.ScopeManager;
import com.rzagorski.thingithubclient.data.interactor.SearchRepositoryInteractor;
import com.rzagorski.thingithubclient.data.interactor.SearchUserInteractor;
import com.rzagorski.thingithubclient.model.app.GithubItem;
import com.rzagorski.thingithubclient.model.app.GithubItemComparator;
import com.rzagorski.thingithubclient.model.app.GithubRepository;
import com.rzagorski.thingithubclient.model.app.GithubUser;
import com.rzagorski.thingithubclient.utils.abstracts.BasePresenter;
import com.rzagorski.thingithubclient.utils.observable.ObservableUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dagger.Lazy;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func2;

/**
 * Created by Robert Zagórski on 2016-12-08.
 */

public class SearchDataPresenterImpl extends BasePresenter<SearchData.View> implements SearchData.Presenter {

    private SearchUserInteractor mSearchUserInteractor;
    private SearchRepositoryInteractor mSearchRepositoryInteractor;
    private SearchSubscriber searchSubscriber;

    public SearchDataPresenterImpl(Lazy<SearchData.View> viewProvider,
                                   SearchUserInteractor searchUserInteractor,
                                   SearchRepositoryInteractor searchRepositoryInteractor,
                                   ScopeManager scopeManager) {
        super(viewProvider);
        this.mSearchUserInteractor = searchUserInteractor;
        this.mSearchRepositoryInteractor = searchRepositoryInteractor;
    }

    @Override
    public void onSearchQuery(String query) {
        Observable<List<GithubUser>> userSearchObs = mSearchUserInteractor.build(query)
                .onErrorResumeNext(Observable.just(Collections.<GithubUser>emptyList()));
        Observable<List<GithubRepository>> repositorySearchObs = mSearchRepositoryInteractor.build(query)
                .onErrorResumeNext(Observable.just(Collections.<GithubRepository>emptyList()));

        Observable<List<GithubItem>> searchObs = mergeResults(userSearchObs, repositorySearchObs);

        searchSubscriber = new SearchSubscriber();
        getSubscription().add(searchSubscriber);
        searchObs.subscribe(searchSubscriber);
        getView().showLoading();
    }


    @Override
    public void onCancelSearch() {
        if (searchSubscriber != null && !searchSubscriber.isUnsubscribed()) {
            searchSubscriber.unsubscribe();
        }
        getView().clearResults();
    }

    @Override
    public void onListEnd() {
        if (!mSearchUserInteractor.isMore() && !mSearchRepositoryInteractor.isMore()) {
            return;
        }
        Observable<List<GithubUser>> userSearchObs;
        if (mSearchUserInteractor.isMore()) {
            userSearchObs = mSearchUserInteractor.getNextPage()
                    .onErrorResumeNext(Observable.just(Collections.<GithubUser>emptyList()));
        } else {
            userSearchObs = Observable.empty();
        }
        Observable<List<GithubRepository>> repositorySearchObs;
        if (mSearchRepositoryInteractor.isMore()) {
            repositorySearchObs = mSearchRepositoryInteractor.getNextPage()
                    .onErrorResumeNext(Observable.just(Collections.<GithubRepository>emptyList()));
        } else {
            repositorySearchObs = Observable.empty();
        }

        Observable<List<GithubItem>> searchObs = mergeResults(userSearchObs, repositorySearchObs);

        searchSubscriber = new SearchSubscriber();
        getSubscription().add(searchSubscriber);
        searchObs.subscribe(searchSubscriber);
        getView().showLoading();
    }

    @Override
    public void onUserClick(GithubUser githubUser) {

    }

    private Observable<List<GithubItem>> mergeResults(Observable<List<GithubUser>> userSearchObs,
                                                      Observable<List<GithubRepository>> repositorySearchObs) {
        return userSearchObs
                .zipWith(repositorySearchObs,
                        new Func2<List<GithubUser>, List<GithubRepository>, List<GithubItem>>() {
                            @Override
                            public List<GithubItem> call(List<GithubUser> githubUsers, List<GithubRepository> githubRepositories) {
                                return mergeLists(githubUsers, githubRepositories);
                            }
                        })
                .compose(ObservableUtils.<List<GithubItem>>applySchedulers());
    }

    private List<GithubItem> mergeLists(List<GithubUser> githubUsers, List<GithubRepository> githubRepositories) {
        List<GithubItem> githubItemList = new ArrayList<>(githubUsers.size() + githubRepositories.size());
        githubItemList.addAll(githubUsers);
        githubItemList.addAll(githubRepositories);
        Collections.sort(githubItemList, new GithubItemComparator());
        return githubItemList;
    }

    public class SearchSubscriber extends Subscriber<List<GithubItem>> {

        @Override
        public void onCompleted() {
            getView().hideLoading();
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
            getView().onSearchResultsError(e);
        }

        @Override
        public void onNext(List<GithubItem> githubItemList) {
            getView().onSearchResults(githubItemList);
        }
    }
}
