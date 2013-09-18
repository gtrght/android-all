package com.othelle.android.action;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;

/**
 * Temporary implementation of BasicAsyncAction
 * author: v.vlasov
 */
public abstract class BaseAsyncAction<In, Out> extends AsyncAction<In, Out> implements UIAction<In, Out> {
    private String title;
    private Drawable icon;
    private String subTitle;

    private boolean cancellable;

    private boolean showDialog = true;

    public BaseAsyncAction(Context context) {
        this(context, null, null);
    }

    public BaseAsyncAction(Context context, String title) {
        this(context, title, null);

    }

    public BaseAsyncAction(Context context, String title, Drawable icon) {
        super(context);
        this.title = title;
        this.icon = icon;
        setProgressObserver(new ProgressObserver() {
            private Cancellable cancellable;

            @Override
            public void setCancellable(Cancellable cancellable) {
                this.cancellable = cancellable;
            }

            @Override
            public void setProgress(int percentage) {
            }

            @Override
            public void started() {
                if (showDialog) {
                    final ProgressDialog progressDialog = ProgressDialog.show(BaseAsyncAction.this.context, null, getTitle());
                    if (BaseAsyncAction.this.cancellable) {
                        progressDialog.setCancelable(true);
                        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialogInterface) {
                                requestCancel();
                            }
                        });
                    }
                }
            }

            @Override
            public void cancelled() {
            }

            @Override
            public void completed() {
            }
        });
    }

    public BaseAsyncAction<In, Out> setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
        return this;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}
