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

    /**
     * Test created by Robert Zagorski on 09.12.2016
     * Test modified by Robert Zagorski on 12.12.2016
     */
    @Test
    public void testSearchUserByQuerySuccess() throws Exception {
        TestSubscriber testSubscriber = new TestSubscriber();
        Observable<ApiSearchUser> searchUserObservable = githubApi.getSearchUser(testedString, null);
        searchUserObservable.subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertCompleted();
    }

    /**
     * Test created by Robert Zagorski on 09.12.2016
     * Test modified by Robert Zagorski on 12.12.2016
     */
    @Test
    public void testSearchUserByQueryNotNull() throws Exception {
        TestSubscriber testSubscriber = new TestSubscriber();
        Observable<ApiSearchUser> searchUserObservable = githubApi.getSearchUser(testedString, null);
        searchUserObservable.subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent();
        ApiSearchUser apiSearchUser = (ApiSearchUser) testSubscriber.getOnNextEvents().get(0);
        assertNotNull(apiSearchUser.getItems());
    }

    /**
     * Test created by Robert Zagorski on 09.12.2016
     * Test modified by Robert Zagorski on 12.12.2016
     */
    @Test
    public void testSearchUserByQueryUsersNotNull() throws Exception {
        TestSubscriber testSubscriber = new TestSubscriber();
        Observable<ApiSearchUser> searchUserObservable = githubApi.getSearchUser(testedString, null);
        searchUserObservable.subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent();
        ApiSearchUser apiSearchUser = (ApiSearchUser) testSubscriber.getOnNextEvents().get(0);
        assertTrue(apiSearchUser.getItems().size() > 0);
    }

    /**
     * Test created by Robert Zagorski on 09.12.2016
     * Test modified by Robert Zagorski on 12.12.2016
     */
    @Test
    public void testSearchUserByQueryError() throws Exception {
        TestSubscriber testSubscriber = new TestSubscriber();
        Observable<ApiSearchUser> searchUserObservable = githubApi.getSearchUser("", null);
        searchUserObservable.subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertError(HttpException.class);
    }

    /**
     * Test created by Robert Zagorski on 12.12.2016
     */
    @Test
    public void testSearchUsersByQueryPaging() throws Exception {
        TestSubscriber testSubscriber = new TestSubscriber();
        Observable<ApiSearchUser> searchRepositoryObservable = githubApi.getSearchUser(testedString, 0);
        searchRepositoryObservable.subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent();
        ApiSearchUser apiSearchUser = (ApiSearchUser) testSubscriber.getOnNextEvents().get(0);
        assertTrue(apiSearchUser.getItems().size() > 0);
    }

    /**
     * Test created by Robert Zagorski on 12.12.2016
     */
    @Test
    public void testSearchUsersByQueryPagingNextPage() throws Exception {
        TestSubscriber testSubscriber = new TestSubscriber();
        Observable<ApiSearchUser> searchRepositoryObservable = githubApi.getSearchUser(testedString, 1);
        searchRepositoryObservable.subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent();
        ApiSearchUser apiSearchUser = (ApiSearchUser) testSubscriber.getOnNextEvents().get(0);
        assertTrue(apiSearchUser.getItems().size() > 0);
    }

    /**
     * Test created by Robert Zagorski on 09.12.2016
     * Test modified by Robert Zagorski on 12.12.2016
     */
    @Test
    public void testSearchRepositoryByQuerySuccess() throws Exception {
        TestSubscriber testSubscriber = new TestSubscriber();
        Observable<ApiSearchRepository> searchRepositoryObservable = githubApi.getSearchRepository(testedString, null);
        searchRepositoryObservable.subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertCompleted();
    }

    /**
     * Test created by Robert Zagorski on 09.12.2016
     * Test modified by Robert Zagorski on 12.12.2016
     */
    @Test
    public void testSearchRepositoryByQueryNotNull() throws Exception {
        TestSubscriber testSubscriber = new TestSubscriber();
        Observable<ApiSearchRepository> searchRepositoryObservable = githubApi.getSearchRepository(testedString, null);
        searchRepositoryObservable.subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent();
        ApiSearchRepository apiSearchRepository = (ApiSearchRepository) testSubscriber.getOnNextEvents().get(0);
        assertNotNull(apiSearchRepository.getItems());
    }

    /**
     * Test created by Robert Zagorski on 09.12.2016
     * Test modified by Robert Zagorski on 12.12.2016
     */
    @Test
    public void testSearchRepositoryByQueryRepositoryNotNull() throws Exception {
        TestSubscriber testSubscriber = new TestSubscriber();
        Observable<ApiSearchRepository> searchRepositoryObservable = githubApi.getSearchRepository(testedString, null);
        searchRepositoryObservable.subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent();
        ApiSearchRepository apiSearchUser = (ApiSearchRepository) testSubscriber.getOnNextEvents().get(0);
        assertTrue(apiSearchUser.getItems().size() > 0);
    }

    /**
     * Test created by Robert Zagorski on 09.12.2016
     * Test modified by Robert Zagorski on 12.12.2016
     */
    @Test
    public void testSearchRepositoryByQueryError() throws Exception {
        TestSubscriber testSubscriber = new TestSubscriber();
        Observable<ApiSearchRepository> searchRepositoryObservable = githubApi.getSearchRepository("", null);
        searchRepositoryObservable.subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertError(HttpException.class);
    }

    /**
     * Test created by Robert Zagorski on 12.12.2016
     */
    @Test
    public void testSearchRepositoryByQueryPaging() throws Exception {
        TestSubscriber testSubscriber = new TestSubscriber();
        Observable<ApiSearchRepository> searchRepositoryObservable = githubApi.getSearchRepository(testedString, 0);
        searchRepositoryObservable.subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent();
        ApiSearchRepository apiSearchRepository = (ApiSearchRepository) testSubscriber.getOnNextEvents().get(0);
        assertTrue(apiSearchRepository.getItems().size() > 0);
    }

    /**
     * Test created by Robert Zagorski on 12.12.2016
     */
    @Test
    public void testSearchRepositoryByQueryPagingNextPage() throws Exception {
        TestSubscriber testSubscriber = new TestSubscriber();
        Observable<ApiSearchRepository> searchRepositoryObservable = githubApi.getSearchRepository(testedString, 1);
        searchRepositoryObservable.subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent();
        ApiSearchRepository apiSearchRepository = (ApiSearchRepository) testSubscriber.getOnNextEvents().get(0);
        assertTrue(apiSearchRepository.getItems().size() > 0);
    }
}
