package com.rzagorski.thingithubclient.data.interactor;

import com.rzagorski.thingithubclient.data.api.ApiManager;
import com.rzagorski.thingithubclient.model.api.ApiSearchUser;
import com.rzagorski.thingithubclient.model.app.GithubUser;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;

import static junit.framework.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

/**
 * Created by Robert Zagórski on 2016-12-09.
 */
@RunWith(MockitoJUnitRunner.class)
public class SearchUserInteractorTest {
    @Mock ApiManager apiManager;

    private SearchUserInteractor searchUserInteractor;

    @Before
    public void setUp() throws Exception {
        searchUserInteractor = new SearchUserInteractor(apiManager);
    }

    @After
    public void tearDown() throws Exception {
        searchUserInteractor = null;
    }

    /**
     * Test created by Robert Zagorski on 09.12.2016
     * Test modified by Robert Zagorski on 12.12.2016
     */
    @Test
    public void testApiInterationWithApiManager() throws Exception {
        Mockito.when(apiManager.getUsersBySearchQuery(anyString(), anyInt())).thenReturn(Observable.<ApiSearchUser>empty());
        String testedString = "AnyQuery";
        searchUserInteractor.build(testedString);
        Mockito.verify(apiManager, Mockito.atLeastOnce()).getUsersBySearchQuery(anyString(), anyInt())
        ;
    }

    /**
     * Test created by Robert Zagorski on 09.12.2016
     * Test modified by Robert Zagorski on 12.12.2016
     */
    @Test
    public void testApiInteractionWithCorrectSearchQuery() throws Exception {
        String testedString = "AnyQuery";
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        Mockito.when(apiManager.getUsersBySearchQuery(argumentCaptor.capture(), anyInt())).thenReturn(Observable.<ApiSearchUser>empty());
        searchUserInteractor.build(testedString);
        assertEquals(testedString, argumentCaptor.getValue());
    }

    /**
     * Test created by Robert Zagorski on 09.12.2016
     * Test modified by Robert Zagorski on 12.12.2016
     */
    @Test
    public void testInteractionCompletedSuccessfully() throws Exception {
        String testedString = "AnyString";
        List<GithubUser> githubUserList = new ArrayList<>();
        ApiSearchUser apiSearchUser = Mockito.mock(ApiSearchUser.class);
        Mockito.when(apiSearchUser.getItems()).thenReturn(githubUserList);
        Mockito.when(apiManager.getUsersBySearchQuery(anyString(), anyInt())).thenReturn(Observable.just(apiSearchUser));
        Observable<List<GithubUser>> githubUserListObservable = searchUserInteractor.build(testedString);
        TestSubscriber<List<GithubUser>> testSubscriber = new TestSubscriber<>();
        githubUserListObservable.subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertNoErrors();
    }

    /**
     * Test created by Robert Zagorski on 09.12.2016
     * Test modified by Robert Zagorski on 12.12.2016
     */
    @Test
    public void testOnNextEventsCount() throws Exception {
        String testedString = "AnyString";
        List<GithubUser> githubUserList = new ArrayList<>();
        ApiSearchUser apiSearchUser = Mockito.mock(ApiSearchUser.class);
        Mockito.when(apiSearchUser.getItems()).thenReturn(githubUserList);
        Mockito.when(apiManager.getUsersBySearchQuery(anyString(), anyInt())).thenReturn(Observable.just(apiSearchUser));
        Observable<List<GithubUser>> githubUserListObservable = searchUserInteractor.build(testedString);
        TestSubscriber<List<GithubUser>> testSubscriber = new TestSubscriber<>();
        githubUserListObservable.subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertValueCount(1);
    }

    /**
     * Test created by Robert Zagorski on 09.12.2016
     * Test modified by Robert Zagorski on 12.12.2016
     */
    @Test
    public void testNoErrors() throws Exception {
        String testedString = "AnyString";
        List<GithubUser> githubUserList = new ArrayList<>();
        ApiSearchUser apiSearchUser = Mockito.mock(ApiSearchUser.class);
        Mockito.when(apiSearchUser.getItems()).thenReturn(githubUserList);
        Mockito.when(apiManager.getUsersBySearchQuery(anyString(), anyInt())).thenReturn(Observable.just(apiSearchUser));
        Observable<List<GithubUser>> githubUserListObservable = searchUserInteractor.build(testedString);
        TestSubscriber<List<GithubUser>> testSubscriber = new TestSubscriber<>();
        githubUserListObservable.subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertNoErrors();
    }

    /**
     * Test created by Robert Zagorski on 09.12.2016
     * Test modified by Robert Zagorski on 12.12.2016
     */
    @Test
    public void testAssertCompletedSuccessfully() throws Exception {
        String testedString = "AnyString";
        List<GithubUser> githubUserList = new ArrayList<>();
        ApiSearchUser apiSearchUser = Mockito.mock(ApiSearchUser.class);
        Mockito.when(apiSearchUser.getItems()).thenReturn(githubUserList);
        Mockito.when(apiManager.getUsersBySearchQuery(anyString(), anyInt())).thenReturn(Observable.just(apiSearchUser));
        Observable<List<GithubUser>> githubUserListObservable = searchUserInteractor.build(testedString);
        TestSubscriber<List<GithubUser>> testSubscriber = new TestSubscriber<>();
        githubUserListObservable.subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertCompleted();
    }

    /**
     * Test created by Robert Zagorski on 09.12.2016
     * Test modified by Robert Zagorski on 12.12.2016
     */
    @Test
    public void testAssertNotCompletedSuccessfullyWhenNull() throws Exception {
        String testedString = "AnyString";
        Mockito.when(apiManager.getUsersBySearchQuery(anyString(), anyInt())).thenReturn(Observable.<ApiSearchUser>just(null));
        Observable<List<GithubUser>> githubUserListObservable = searchUserInteractor.build(testedString);
        TestSubscriber<List<GithubUser>> testSubscriber = new TestSubscriber<>();
        githubUserListObservable.subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertNotCompleted();
    }

    /**
     * Test created by Robert Zagorski on 12.12.2016
     */
    @Test
    public void testProperPagingAtStart() throws Exception {
        String testedString = "AnyQuery";
        ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);
        Mockito.when(apiManager.getUsersBySearchQuery(anyString(), argumentCaptor.capture())).thenReturn(Observable.<ApiSearchUser>empty());
        searchUserInteractor.build(testedString);
        assertEquals(0, argumentCaptor.getValue().intValue());
    }

    /**
     * Test created by Robert Zagorski on 12.12.2016
     */
    @Test
    public void testProperPagingAtNext() throws Exception {
        String testedString = "AnyQuery";
        ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);
        Mockito.when(apiManager.getUsersBySearchQuery(anyString(), argumentCaptor.capture())).thenReturn(Observable.<ApiSearchUser>empty());
        searchUserInteractor.build(testedString);
        searchUserInteractor.getNextPage();
        List<Integer> values = argumentCaptor.getAllValues();
        assertEquals(1, values.get(values.size() - 1).intValue());
    }

    /**
     * Test created by Robert Zagorski on 12.12.2016
     */
    @Test
    public void testProperPagingAfterClear() throws Exception {
        String testedString = "AnyQuery";
        ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);
        Mockito.when(apiManager.getUsersBySearchQuery(anyString(), argumentCaptor.capture())).thenReturn(Observable.<ApiSearchUser>empty());
        searchUserInteractor.build(testedString);
        searchUserInteractor.getNextPage();
        searchUserInteractor.clearCache();
        searchUserInteractor.build(testedString);
        List<Integer> values = argumentCaptor.getAllValues();
        assertEquals(0, values.get(values.size() - 1).intValue());
    }
}
