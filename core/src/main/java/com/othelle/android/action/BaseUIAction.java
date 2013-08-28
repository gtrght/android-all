package com.othelle.android.action;

import android.graphics.drawable.Drawable;

/**
 * author: v.vlasov
 */
public abstract class BaseUIAction<In, Out> implements UIAction<In, Out> {
    private String title;
    private String subTitle;
    private Drawable icon;

    public BaseUIAction(String title) {
        this(title, null);
    }

    public BaseUIAction(String title, Drawable icon) {
        this(title, null, icon);
    }

    public BaseUIAction(String title, String subTitle, Drawable icon) {
        this.title = title;
        this.subTitle = subTitle;
        this.icon = icon;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getSubTitle() {
        return subTitle;
    }

    @Override
    public Drawable getIcon() {
        return icon;
    }
}
