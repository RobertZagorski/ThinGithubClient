package com.rzagorski.thingithubclient.utils.abstracts;

/**
 * Every presenter in the app must either implement this interface or extend BaseController
 * indicating the MvcView type that wants to be attached with.
 */
public interface Presenter {

    void detachView();
}
