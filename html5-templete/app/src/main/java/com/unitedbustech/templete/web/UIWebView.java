package com.unitedbustech.templete.web;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.lang.reflect.Method;

/**
 * @author yufei0213
 */
public class UIWebView extends WebView {

    protected Context context;

    public UIWebView(Context context) {

        this(context, null);
    }

    public UIWebView(Context context, AttributeSet attrs) {

        super(context, attrs);

        this.context = context;

        initWebViewParams();
    }

    public void show() {

        this.setVisibility(View.VISIBLE);
    }

    public void hide() {

        this.setVisibility(View.GONE);
    }

    public void onReload() {

        this.loadUrl("javascript:Global.onReload();");
    }


    protected void initWebViewParams() {

        getSettings().setTextZoom(100);
        getSettings().setJavaScriptEnabled(true);
        getSettings().setDomStorageEnabled(true);

        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);

        setWebViewClient(webViewclient);
        setWebChromeClient(webChromeClient);

        boolean multiTouch = ((Activity) context).getIntent().getBooleanExtra("multi_touch", false);
        if (!multiTouch)
            setOnTouchListener(touchListener);
        setOnLongClickListener(longClickListener);

        try {

            if (Build.VERSION.SDK_INT >= 16) {

                Class<?> clazz = this.getSettings().getClass();
                Method method = clazz.getMethod("setAllowUniversalAccessFromFileURLs", boolean.class);

                if (method != null)
                    method.invoke(this.getSettings(), true);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private WebViewClient webViewclient = new WebViewClient() {
        @Override
        public void onPageFinished(WebView view, String url) {

        }
    };

    private WebChromeClient webChromeClient = new WebChromeClient() {
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {

            return super.onJsAlert(view, url, message, result);
        }
    };

    private OnLongClickListener longClickListener = new OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {

            return true;
        }
    };

    private OnTouchListener touchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View arg0, MotionEvent arg1) {

            int action = arg1.getAction();

            switch (action) {

                case MotionEvent.ACTION_POINTER_2_DOWN:
                case MotionEvent.ACTION_POINTER_3_DOWN:

                    return true;
            }

            return false;
        }
    };
}
