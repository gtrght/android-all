package com.othelle.android.action;

/**
 * author: v.vlasov
 */
public interface ActionCallback<T> {

    void actionPerformed(Action action, T result);

}
