package com.rzagorski.thingithubclient.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by Robert Zagórski on 2016-11-04.
 */

public class FragmentHelper {

    public static void replaceFragment(FragmentManager fragmentManager, Fragment fragmentToReplace, int fragmentId) {
        FragmentTransaction fragTransaction = fragmentManager.beginTransaction();
        fragTransaction.replace(fragmentId, fragmentToReplace, fragmentToReplace.getClass().getSimpleName());
        fragTransaction.addToBackStack(fragmentToReplace.getClass().getSimpleName());
        fragTransaction.commit();
    }
}
