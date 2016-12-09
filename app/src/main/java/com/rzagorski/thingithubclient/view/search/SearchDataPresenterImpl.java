package com.rzagorski.thingithubclient.view.search;

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

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func2;

/**
 * Created by Robert Zagórski on 2016-12-08.
 */

public class SearchDataPresenterImpl extends BasePresenter<SearchData.View> implements SearchData.Presenter {

    private SearchUserInteractor mSearchUserInteractor;
    private SearchRepositoryInteractor mSearchRepositoryInteractor;

    public SearchDataPresenterImpl(SearchData.View view,
                                   SearchUserInteractor searchUserInteractor,
                                   SearchRepositoryInteractor searchRepositoryInteractor) {
        super(view);
        this.mSearchUserInteractor = searchUserInteractor;
        this.mSearchRepositoryInteractor = searchRepositoryInteractor;
    }

    @Override
    public void onSearchQuery(String query) {
        Observable<List<GithubUser>> userSearchObs = mSearchUserInteractor.build(query)
                .onErrorResumeNext(Observable.just(Collections.<GithubUser>emptyList()));
        Observable<List<GithubRepository>> repositorySearchObs = mSearchRepositoryInteractor.build(query)
                .onErrorResumeNext(Observable.just(Collections.<GithubRepository>emptyList()));

        Observable<List<GithubItem>> searchObs = userSearchObs
                .zipWith(repositorySearchObs,
                        new Func2<List<GithubUser>, List<GithubRepository>, List<GithubItem>>() {
                            @Override
                            public List<GithubItem> call(List<GithubUser> githubUsers, List<GithubRepository> githubRepositories) {
                                return mergeLists(githubUsers, githubRepositories);
                            }
                        })
                .compose(ObservableUtils.<List<GithubItem>>applySchedulers());

        SearchSubscriber searchSubscriber = new SearchSubscriber();
        getSubscription().add(searchSubscriber);
        searchObs.subscribe(searchSubscriber);
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

        }

        @Override
        public void onError(Throwable e) {
            getView().onSearchResultsError(e);
        }

        @Override
        public void onNext(List<GithubItem> githubItemList) {
            getView().onSearchResults(githubItemList);
        }
    }
}
