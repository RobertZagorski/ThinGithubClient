package com.rzagorski.thingithubclient.view.search;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.inject.Provider;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.timeout;

/**
 * Created by Robert Zagórski on 2016-12-09.
 */
@RunWith(MockitoJUnitRunner.class)
public class SearchPresenterTest {

    @Mock Search.View view;
    @Mock SearchData.Presenter searchDataPresenter;

    private Search.Presenter presenter;

    @Rule
    public ExpectedException expectedExceptionRule = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        Provider<Search.View> viewProvider = new Provider<Search.View>() {
            @Override
            public Search.View get() {
                return view;
            }
        };
        presenter = new SearchPresenterImpl(viewProvider, searchDataPresenter);
    }

    @After
    public void tearDown() throws Exception {
        presenter = null;
    }

    @Test
    public void testSearchDataPresenterInvoked() throws Exception {
        String testedString = "AnyString";
        presenter.onSearchOpened();
        presenter.onQueryChanged(testedString);
        Mockito.verify(searchDataPresenter, timeout(500).atLeastOnce()).onSearchQuery(anyString());
    }

    @Test
    public void testSearchDataPresenterInvokedWithCorrectParameter() throws Exception {
        String testedString = "AnyString";
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        presenter.onSearchOpened();
        presenter.onQueryChanged(testedString);
        Mockito.verify(searchDataPresenter, timeout(500).times(1)).onSearchQuery(argumentCaptor.capture());
        assertEquals(testedString, argumentCaptor.getValue());
    }

    @Test
    public void testSearchDataPresenterNotInvokedWhenQueryEmpty() throws Exception {
        String testedString = "";
        presenter.onQueryChanged(testedString);
        Mockito.verify(searchDataPresenter, never()).onSearchQuery(anyString());
    }

    @Test
    public void testSearchDataPresenterNotInvokedWhenQueryNull() throws Exception {
        String testedString = "";
        presenter.onQueryChanged(testedString);
        Mockito.verify(searchDataPresenter, never()).onSearchQuery(anyString());
    }

    @Test
    public void testSearchDataPresenterExceptionWhenNotOpenedFirst() throws Exception {
        String testedString = "AnyString";
        expectedExceptionRule.expect(NullPointerException.class);
        presenter.onQueryChanged(testedString);
    }

    @Test
    public void testSearchDataPresenterInvokedWithCorrectDelay() throws Exception {
        String testedString = "AnyString";
        presenter.onSearchOpened();
        presenter.onQueryChanged(testedString);
        Mockito.verify(searchDataPresenter, timeout(500)).onSearchQuery(anyString());
    }
}
