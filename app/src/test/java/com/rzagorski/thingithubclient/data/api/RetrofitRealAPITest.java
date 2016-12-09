package com.rzagorski.thingithubclient.data.api;

import com.rzagorski.thingithubclient.data.api.retrofit.RetrofitApiFactory;
import com.rzagorski.thingithubclient.model.api.ApiSearchRepository;
import com.rzagorski.thingithubclient.model.api.ApiSearchUser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Test real interaction with github API. Due to rate limit for the IP address, this test is not intended
 * for automation tests, rather to be checked rarely to assert that ie. API models are unchanged.
 * <p>
 * Created by Robert Zagórski on 2016-12-09.
 */
@RunWith(MockitoJUnitRunner.class)
public class RetrofitRealAPITest {

    GithubApi githubApi = RetrofitApiFactory.makeApiManager(GithubApi.BASE_URL);
    String testedString = "square";

    @Test
    public void testSearchUserByQuerySuccess() throws Exception {
        TestSubscriber testSubscriber = new TestSubscriber();
        Observable<ApiSearchUser> searchUserObservable = githubApi.getSearchUser(testedString);
        searchUserObservable.subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertCompleted();
    }

    @Test
    public void testSearchUserByQueryNotNull() throws Exception {
        TestSubscriber testSubscriber = new TestSubscriber();
        Observable<ApiSearchUser> searchUserObservable = githubApi.getSearchUser(testedString);
        searchUserObservable.subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent();
        ApiSearchUser apiSearchUser = (ApiSearchUser) testSubscriber.getOnNextEvents().get(0);
        assertNotNull(apiSearchUser.getItems());
    }

    @Test
    public void testSearchUserByQueryUsersNotNull() throws Exception {
        TestSubscriber testSubscriber = new TestSubscriber();
        Observable<ApiSearchUser> searchUserObservable = githubApi.getSearchUser(testedString);
        searchUserObservable.subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent();
        ApiSearchUser apiSearchUser = (ApiSearchUser) testSubscriber.getOnNextEvents().get(0);
        assertTrue(apiSearchUser.getItems().size() > 0);
    }

    @Test
    public void testSearchUserByQueryError() throws Exception {
        TestSubscriber testSubscriber = new TestSubscriber();
        Observable<ApiSearchUser> searchUserObservable = githubApi.getSearchUser("");
        searchUserObservable.subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertError(HttpException.class);
    }

    @Test
    public void testSearchRepositoryByQuerySuccess() throws Exception {
        TestSubscriber testSubscriber = new TestSubscriber();
        Observable<ApiSearchRepository> searchRepositoryObservable = githubApi.getSearchRepository(testedString);
        searchRepositoryObservable.subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertCompleted();
    }

    @Test
    public void testSearchRepositoryByQueryNotNull() throws Exception {
        TestSubscriber testSubscriber = new TestSubscriber();
        Observable<ApiSearchRepository> searchRepositoryObservable = githubApi.getSearchRepository(testedString);
        searchRepositoryObservable.subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent();
        ApiSearchRepository apiSearchRepository = (ApiSearchRepository) testSubscriber.getOnNextEvents().get(0);
        assertNotNull(apiSearchRepository.getItems());
    }

    @Test
    public void testSearchRepositoryByQueryRepositoryNotNull() throws Exception {
        TestSubscriber testSubscriber = new TestSubscriber();
        Observable<ApiSearchRepository> searchRepositoryObservable = githubApi.getSearchRepository(testedString);
        searchRepositoryObservable.subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent();
        ApiSearchRepository apiSearchUser = (ApiSearchRepository) testSubscriber.getOnNextEvents().get(0);
        assertTrue(apiSearchUser.getItems().size() > 0);
    }

    @Test
    public void testSearchRepositoryByQueryError() throws Exception {
        TestSubscriber testSubscriber = new TestSubscriber();
        Observable<ApiSearchRepository> searchRepositoryObservable = githubApi.getSearchRepository("");
        searchRepositoryObservable.subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertError(HttpException.class);
    }
}
