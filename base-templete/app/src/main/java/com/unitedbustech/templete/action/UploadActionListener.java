package com.unitedbustech.templete.action;

/**
 * @author yufei0213
 */
public interface UploadActionListener {

    void onSuccess();

    void onFailure(int code, String msg);
}
