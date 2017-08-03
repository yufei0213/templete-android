package com.unitedbustech.templete.action;

import java.io.File;

/**
 * Created by yufei0213 on 2017/1/16.
 */
public interface DownloadActionListener {

    void onSuccess(File file);

    void onFailure(int code, String msg);
}
