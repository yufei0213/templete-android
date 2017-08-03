package com.unitedbustech.templete.view;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.unitedbustech.templete.R;

/**
 * @author yufei0213
 */
public class ContentView extends LinearLayout {

    private View statusBar;
    private View statusBarTop;

    private FrameLayout container;

    public ContentView(Activity activity) {

        this(activity, null);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT)
            return;

        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setStatusBarColor(activity, R.color.colorPrimary, false);
    }

    public ContentView(Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.content_view, this, true);

        statusBar = this.findViewById(R.id.status_bar);
        statusBarTop = this.findViewById(R.id.status_bar_top);
        container = (FrameLayout) this.findViewById(R.id.container);
    }

    public void setContentView(View view) {

        container.addView(view);
    }

    public void setStatusBarColor(Context context, int colorId, boolean isFullScreen) {

        int height = getStatusBarHeight(context);

        if (isFullScreen) {

            LinearLayout.LayoutParams statusBarParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0);
            FrameLayout.LayoutParams statusBarTopParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, height);

            statusBar.setLayoutParams(statusBarParams);

            statusBarTop.setLayoutParams(statusBarTopParams);
            statusBarTop.setBackgroundColor(getResources().getColor(colorId));
        } else {

            LinearLayout.LayoutParams statusBarParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height);
            FrameLayout.LayoutParams statusBarTopParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, 0);

            statusBarTop.setLayoutParams(statusBarTopParams);

            statusBar.setLayoutParams(statusBarParams);
            statusBar.setBackgroundColor(getResources().getColor(colorId));
        }
    }

    private int getStatusBarHeight(Context context) {

        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");

        if (resourceId > 0)
            result = context.getResources().getDimensionPixelSize(resourceId);

        return result;
    }
}
