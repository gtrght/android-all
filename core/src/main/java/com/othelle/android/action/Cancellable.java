package com.othelle.android.action;

/**
 * author: v.vlasov
 */
public interface Cancellable {

    public boolean isCancellable();

    public void requestCancel();

}
