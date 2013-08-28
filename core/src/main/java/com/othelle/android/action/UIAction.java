package com.othelle.android.action;

import android.graphics.drawable.Drawable;

/**
 * author: v.vlasov
 */
public interface UIAction<In, Out> extends Action<In, Out> {
    String getTitle();

    String getSubTitle();

    Drawable getIcon();
}
