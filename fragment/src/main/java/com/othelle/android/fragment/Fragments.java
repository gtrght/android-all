package com.othelle.android.fragment;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * author: v.vlasov
 */
public class Fragments {

    public static void showDialog(FragmentActivity activity, DialogFragment fragment, String tag) {
        android.support.v4.app.FragmentManager manager = activity.getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = manager.beginTransaction();
        Fragment prev = manager.findFragmentByTag(tag);
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        fragment.show(ft, tag);
    }
}
