package com.othelle.android.action;

/**
 * author: v.vlasov
 */
public interface Action<I, T> {
    T performAction(I ... param);
}
