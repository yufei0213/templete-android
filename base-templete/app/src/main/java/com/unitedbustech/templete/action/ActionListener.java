package com.unitedbustech.templete.action;

/**
 * @author yufei0213
 */
public interface ActionListener<T> {

    void onStart();

    void onCancel();

    void onSuccess(T result);

    void onFailure(int code, String msg);
}
