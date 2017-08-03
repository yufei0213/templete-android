package com.unitedbustech.templete.action;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.unitedbustech.templete.App;
import com.unitedbustech.templete.enumer.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yufei0213
 */
public class DownloadAction {

    private static final String TAG = "DownloadAction";

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private String requestUrl;
    private DownloadActionListener listener;

    public DownloadAction(String requestUrl, DownloadActionListener listener) {

        this.requestUrl = requestUrl;
        this.listener = listener;
    }

    public void execute(Map<String, Object> paramMap) {

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        if (paramMap == null)
            paramMap = new HashMap<>();

        logger.debug(requestUrl);

        if (!paramMap.isEmpty())
            for (Map.Entry<String, Object> entry : paramMap.entrySet())
                params.put(entry.getKey(), entry.getValue());

        client.get(requestUrl, params, new FileAsyncHttpResponseHandler(App.getContext()) {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, File file) {

                listener.onFailure(Status.REQUEST_TIMEOUT.getCode(), Status.REQUEST_TIMEOUT.getMsg());
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, Throwable throwable, File file) {

                if (statusCode != 200) {

                    listener.onFailure(Status.UNKNOWN_ERROR.getCode(), Status.UNKNOWN_ERROR.getMsg());
                    return;
                }

                listener.onSuccess(file);
            }
        });
    }
}
