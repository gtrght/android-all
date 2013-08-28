package com.othelle.android.action;

import android.support.v4.app.FragmentActivity;
import com.othelle.android.fragment.ContextMenuFragment;
import com.othelle.android.fragment.Fragments;

/**
 * author: v.vlasov
 */
public class ContextMenuAction implements Action<UIAction, Object> {
    private FragmentActivity activity;

    public ContextMenuAction() {
    }

    public ContextMenuAction(FragmentActivity activity){
        this.activity = activity;
    }

    public ContextMenuAction setActivity(FragmentActivity activity) {
        this.activity = activity;
        return this;
    }

    @Override
    public Object performAction(UIAction... param) {
        ContextMenuFragment menuFragment = ContextMenuFragment.create(param);
        Fragments.showDialog(activity, menuFragment, "context_menu");
        return null;
    }
}
