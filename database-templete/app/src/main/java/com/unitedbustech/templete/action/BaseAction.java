package com.unitedbustech.templete.action;

import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;
import com.unitedbustech.templete.enumer.Status;
import com.unitedbustech.templete.util.JsonUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yufei0213
 */
public abstract class BaseAction<T> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public static final String TAG = "BaseAction";

    protected String requestUrl;    // 请求的URL地址
    protected T result = null;     // 服务返回结果
    protected boolean isPost;     //是否是post请求
    protected int maxRetries = 0; //失败重试
    protected int timeOut = 10 * 1000; //超时时间

    protected ActionListener<T> listener;   // handler服务执行监听器

    private AsyncHttpClient client;

    public void execute(Map<String, Object> params) {

        if (params == null) {

            params = new HashMap<>();
        }

        if (isPost) {

            doPost(params);
        } else {

            doGet(params);
        }

        if (!isPost) {

            String temp = "";
            if (!params.isEmpty())
                for (Map.Entry<String, Object> entry : params.entrySet())
                    temp += entry.getKey() + "=" + entry.getValue() + "&";

            logger.debug(requestUrl + "?" + temp.substring(0, temp.length() - 1));
        } else {

            logger.debug(requestUrl);
        }
    }

    public void executeSync(Map<String, Object> params) {

        if (params == null) {

            params = new HashMap<>();
        }

        if (isPost) {

            doPostSync(params);
        } else {

            doGetSync(params);
        }

        if (!isPost) {

            String temp = "";
            if (!params.isEmpty())
                for (Map.Entry<String, Object> entry : params.entrySet())
                    temp += entry.getKey() + "=" + entry.getValue() + "&";

            logger.debug(requestUrl + "?" + temp.substring(0, temp.length() - 1));
        } else {

            logger.debug(requestUrl);
        }
    }

    public void cancel() {

        if (client != null) {

            client.cancelAllRequests(true);
        }
    }

    private void doGet(Map<String, Object> paramMap) {

        client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        if (!paramMap.isEmpty())
            for (Map.Entry<String, Object> entry : paramMap.entrySet())
                params.put(entry.getKey(), entry.getValue());

        client.setMaxRetriesAndTimeout(maxRetries, timeOut); //失败不重试，超时时间为10秒
        client.get(requestUrl, params, asyncHttpResponseHandler);
    }

    private void doGetSync(Map<String, Object> paramMap) {

        client = new SyncHttpClient();
        RequestParams params = new RequestParams();

        if (!paramMap.isEmpty())
            for (Map.Entry<String, Object> entry : paramMap.entrySet())
                params.put(entry.getKey(), entry.getValue());

        client.setMaxRetriesAndTimeout(maxRetries, timeOut); //失败不重试，超时时间为10秒
        client.get(requestUrl, params, asyncHttpResponseHandler);
    }

    private void doPost(Map<String, Object> paramMap) {

        client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        if (!paramMap.isEmpty())
            for (Map.Entry<String, Object> entry : paramMap.entrySet())
                params.put(entry.getKey(), entry.getValue());

        client.setMaxRetriesAndTimeout(maxRetries, timeOut); //失败不重试，超时时间为10秒
        client.post(requestUrl, params, asyncHttpResponseHandler);
    }

    private void doPostSync(Map<String, Object> paramMap) {

        client = new SyncHttpClient();
        RequestParams params = new RequestParams();

        if (!paramMap.isEmpty())
            for (Map.Entry<String, Object> entry : paramMap.entrySet())
                params.put(entry.getKey(), entry.getValue());

        client.setMaxRetriesAndTimeout(maxRetries, timeOut); //失败不重试，超时时间为10秒
        client.post(requestUrl, params, asyncHttpResponseHandler);
    }

    private AsyncHttpResponseHandler asyncHttpResponseHandler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {

            if (statusCode != 200) {

                onFailureEvent(Status.UNKNOWN_ERROR.getCode(), Status.UNKNOWN_ERROR.getMsg());
                return;
            }

            JSONObject result = JsonUtil.parseObject(new String(responseBody));

            if (JsonUtil.getInt(result, "code") != Status.SUCCESS.getCode()) {

                onFailureEvent(JsonUtil.getInt(result, "code"), JsonUtil.getString(result, "msg"));
                return;
            }

            onSuccessEvent(result);
        }

        @Override
        public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {

            onFailureEvent(Status.REQUEST_TIMEOUT.getCode(), Status.REQUEST_TIMEOUT.getMsg());
        }

        @Override
        public void onStart() {

            if (listener != null)
                listener.onStart();
        }

        @Override
        public void onCancel() {

            if (listener != null)
                listener.onCancel();
        }
    };

    private void onSuccessEvent(JSONObject json) {

        result = parseJsonObject(JsonUtil.getJsonObject(json, "result"));

        if (listener != null) {

            listener.onSuccess(result);
        }
    }

    private void onFailureEvent(int code, String msg) {

        if (listener != null) {

            listener.onFailure(code, msg);
        }
    }

    protected abstract T parseJsonObject(JSONObject json);
}
