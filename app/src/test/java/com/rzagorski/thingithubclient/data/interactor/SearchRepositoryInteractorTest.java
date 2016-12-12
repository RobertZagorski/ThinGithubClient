package com.rzagorski.thingithubclient.data.interactor;

import com.rzagorski.thingithubclient.data.api.ApiManager;
import com.rzagorski.thingithubclient.model.api.ApiSearchRepository;
import com.rzagorski.thingithubclient.model.app.GithubRepository;

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
public class SearchRepositoryInteractorTest {
    @Mock ApiManager apiManager;

    private SearchRepositoryInteractor searchRepositoryInteractor;

    @Before
    public void setUp() throws Exception {
        searchRepositoryInteractor = new SearchRepositoryInteractor(apiManager);
    }

    @After
    public void tearDown() throws Exception {
        searchRepositoryInteractor = null;
    }

    @Test
    public void testApiInterationWithApiManager() throws Exception {
        Mockito.when(apiManager.getRepositoriesBySearchQuery(anyString(), anyInt())).thenReturn(Observable.<ApiSearchRepository>empty());
        String testedString = "AnyQuery";
        searchRepositoryInteractor.build(testedString);
        Mockito.verify(apiManager, Mockito.atLeastOnce()).getRepositoriesBySearchQuery(anyString(), anyInt());
    }

    @Test
    public void testApiInteractionWithCorrectSearchQuery() throws Exception {
        String testedString = "AnyQuery";
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        Mockito.when(apiManager.getRepositoriesBySearchQuery(argumentCaptor.capture(), anyInt())).thenReturn(Observable.<ApiSearchRepository>empty());
        searchRepositoryInteractor.build(testedString);
        assertEquals(testedString, argumentCaptor.getValue());
    }

    @Test
    public void testInteractionCompletedSuccessfully() throws Exception {
        String testedString = "AnyString";
        List<GithubRepository> githubRepositoryList = new ArrayList<>();
        ApiSearchRepository apiSearchRepository = Mockito.mock(ApiSearchRepository.class);
        Mockito.when(apiSearchRepository.getItems()).thenReturn(githubRepositoryList);
        Mockito.when(apiManager.getRepositoriesBySearchQuery(anyString(), anyInt())).thenReturn(Observable.just(apiSearchRepository));
        Observable<List<GithubRepository>> githubRepositoryListObservable = searchRepositoryInteractor.build(testedString);
        TestSubscriber<List<GithubRepository>> testSubscriber = new TestSubscriber<>();
        githubRepositoryListObservable.subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertNoErrors();
    }

    @Test
    public void testOnNextEventsCount() throws Exception {
        String testedString = "AnyString";
        List<GithubRepository> githubRepositoryList = new ArrayList<>();
        ApiSearchRepository apiSearchRepository = Mockito.mock(ApiSearchRepository.class);
        Mockito.when(apiSearchRepository.getItems()).thenReturn(githubRepositoryList);
        Mockito.when(apiManager.getRepositoriesBySearchQuery(anyString(), anyInt())).thenReturn(Observable.just(apiSearchRepository));
        Observable<List<GithubRepository>> githubRepositoryListObservable = searchRepositoryInteractor.build(testedString);
        TestSubscriber<List<GithubRepository>> testSubscriber = new TestSubscriber<>();
        githubRepositoryListObservable.subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertValueCount(1);
    }

    @Test
    public void testNoErrors() throws Exception {
        String testedString = "AnyString";
        List<GithubRepository> githubRepositoryList = new ArrayList<>();
        ApiSearchRepository apiSearchRepository = Mockito.mock(ApiSearchRepository.class);
        Mockito.when(apiSearchRepository.getItems()).thenReturn(githubRepositoryList);
        Mockito.when(apiManager.getRepositoriesBySearchQuery(anyString(), anyInt())).thenReturn(Observable.just(apiSearchRepository));
        Observable<List<GithubRepository>> githubRepositoryListObservable = searchRepositoryInteractor.build(testedString);
        TestSubscriber<List<GithubRepository>> testSubscriber = new TestSubscriber<>();
        githubRepositoryListObservable.subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertNoErrors();
    }

    @Test
    public void testAssertCompletedSuccessfully() throws Exception {
        String testedString = "AnyString";
        List<GithubRepository> githubRepositoryList = new ArrayList<>();
        ApiSearchRepository apiSearchRepository = Mockito.mock(ApiSearchRepository.class);
        Mockito.when(apiSearchRepository.getItems()).thenReturn(githubRepositoryList);
        Mockito.when(apiManager.getRepositoriesBySearchQuery(anyString(), anyInt())).thenReturn(Observable.just(apiSearchRepository));
        Observable<List<GithubRepository>> githubRepositoryListObservable = searchRepositoryInteractor.build(testedString);
        TestSubscriber<List<GithubRepository>> testSubscriber = new TestSubscriber<>();
        githubRepositoryListObservable.subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertCompleted();
    }

    @Test
    public void testAssertNotCompletedSuccessfullyWhenNull() throws Exception {
        String testedString = "AnyString";
        Mockito.when(apiManager.getRepositoriesBySearchQuery(anyString(), anyInt())).thenReturn(Observable.<ApiSearchRepository>just(null));
        Observable<List<GithubRepository>> githubRepositoryListObservable = searchRepositoryInteractor.build(testedString);
        TestSubscriber<List<GithubRepository>> testSubscriber = new TestSubscriber<>();
        githubRepositoryListObservable.subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertNotCompleted();
    }

    @Test
    public void testProperPagingAtStart() throws Exception {
        String testedString = "AnyQuery";
        ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);
        Mockito.when(apiManager.getRepositoriesBySearchQuery(anyString(), argumentCaptor.capture())).thenReturn(Observable.<ApiSearchRepository>empty());
        searchRepositoryInteractor.build(testedString);
        assertEquals(0, argumentCaptor.getValue().intValue());
    }

    @Test
    public void testProperPagingAtNext() throws Exception {
        String testedString = "AnyQuery";
        ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);
        Mockito.when(apiManager.getRepositoriesBySearchQuery(anyString(), argumentCaptor.capture())).thenReturn(Observable.<ApiSearchRepository>empty());
        searchRepositoryInteractor.build(testedString);
        searchRepositoryInteractor.getNextPage();
        List<Integer> values = argumentCaptor.getAllValues();
        assertEquals(1, values.get(values.size() - 1).intValue());
    }

    @Test
    public void testProperPagingAfterClear() throws Exception {
        String testedString = "AnyQuery";
        ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);
        Mockito.when(apiManager.getRepositoriesBySearchQuery(anyString(), argumentCaptor.capture())).thenReturn(Observable.<ApiSearchRepository>empty());
        searchRepositoryInteractor.build(testedString);
        searchRepositoryInteractor.getNextPage();
        searchRepositoryInteractor.clearCache();
        searchRepositoryInteractor.build(testedString);
        List<Integer> values = argumentCaptor.getAllValues();
        assertEquals(0, values.get(values.size() - 1).intValue());
    }
}
