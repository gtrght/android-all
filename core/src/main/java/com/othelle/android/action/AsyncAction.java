package com.othelle.android.action;

import android.content.Context;
import android.os.AsyncTask;

/**
 * author: v.vlasov
 */
public abstract class AsyncAction<In, Out> implements Cancellable, Action<In, Out> {
    protected Context context;

    protected ActionCallback<Out> callback;
    private AsyncTask<In, Exception, Out> asyncTask;
    protected ExceptionHandler exceptionHandler;
    protected boolean cancellable;
    protected ProgressObserver progressObserver;

    public AsyncAction(Context context) {
        this.context = context;
    }


    @Override
    public Out performAction(In ... params) {
        asyncTask = new AsyncTask<In, Exception, Out>() {
            @Override
            protected void onPreExecute() {
                AsyncAction.this.onPreExecute();
                if(progressObserver != null) progressObserver.started();
            }

            @Override
            protected Out doInBackground(In... params) {
                try {
                    return AsyncAction.this.doInBackground(params.length > 0 ? params[0] : null);
                } catch (final Exception e) {
                    publishProgress(e);
                    return null;
                }
            }

            @Override
            protected void onProgressUpdate(Exception... values) {
                AsyncAction.this.onProgressUpdate(values);
            }

            @Override
            @SuppressWarnings(value = "unchecked")
            protected void onPostExecute(Out results) {
                AsyncAction.this.onPostExecute(results);
                if(progressObserver != null) progressObserver.completed();
            }
        };
        asyncTask.execute(params);
        return null;
    }

    protected abstract Out doInBackground(In params) throws Exception;

    public void requestCancel() {
        asyncTask.cancel(true);
        if(progressObserver != null) progressObserver.cancelled();
    }

    protected void onPreExecute() {
        //do nothing
    }

    protected void onProgressUpdate(Exception... values) {
        if (exceptionHandler != null)
            for (Exception value : values) {
                exceptionHandler.handle(value);
            }
    }

    protected void onPostExecute(Out results) {
        callback.actionPerformed(AsyncAction.this, results);
    }

    public AsyncAction<In, Out> setCancellable(boolean cancellable) {
        this.cancellable = cancellable;
        return this;
    }

    public AsyncAction<In, Out> setActionCallback(ActionCallback<Out> callback) {
        this.callback = callback;
        return this;
    }

    public AsyncAction<In, Out> setExceptionHandler(ExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
        return this;
    }

    public AsyncAction<In, Out> setProgressObserver(ProgressObserver progressObserver) {
        this.progressObserver = progressObserver;
        return this;
    }

    public interface ExceptionHandler {
        void handle(Exception e);
    }


}
