package com.unitedbustech.templete.action;

import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.unitedbustech.templete.enumer.Status;
import com.unitedbustech.templete.util.JsonUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yufei0213
 */
public class UploadAction {

    private static final String TAG = "UploadAction";

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private String requestUrl;
    private UploadActionListener listener;

    public UploadAction(String requestUrl, UploadActionListener listener) {

        this.requestUrl = requestUrl;
        this.listener = listener;
    }

    public void execute(RequestParams params) {

        logger.debug(requestUrl);

        if (params == null)
            params = new RequestParams();

        AsyncHttpClient client = new AsyncHttpClient();
        client.post(requestUrl, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {

                if (statusCode != 200) {

                    listener.onFailure(Status.UNKNOWN_ERROR.getCode(), Status.UNKNOWN_ERROR.getMsg());
                    return;
                }

                JSONObject result = JsonUtil.parseObject(new String(responseBody));

                if (JsonUtil.getInt(result, "code") != Status.SUCCESS.getCode()) {

                    listener.onFailure(JsonUtil.getInt(result, "code"), JsonUtil.getString(result, "msg"));
                    return;
                }

                listener.onSuccess();
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {

                listener.onFailure(Status.UNKNOWN_ERROR.getCode(), Status.UNKNOWN_ERROR.getMsg());
            }
        });
    }

}
