package com.unitedbustech.templete.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;

import java.lang.reflect.Method;

/**
 * @author yufei0213
 */
public class ScreenUtil {

    public static float getDensity(Context context) {

        return context.getResources().getDisplayMetrics().density;
    }

    public static int getStatusBarHeight(Context context) {

        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");

        if (resourceId > 0)
            result = context.getResources().getDimensionPixelSize(resourceId);

        return result;
    }

    public static int getScreenWidth(Context context) {

        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(Context context) {

        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getScreenHeightWidthoutVirtualKey(Context context) {

        Activity activity = (Activity) context;

        int height = activity.getWindowManager().getDefaultDisplay().getHeight();
        return height;
    }

    public static int getScreenHeightWidthVirtualKey(Context context) {

        Activity activity = (Activity) context;

        int height = 0;
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();

        @SuppressWarnings("rawtypes")
        Class c;

        try {

            c = Class.forName("android.view.Display");

            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);

            height = dm.heightPixels;
        } catch (Exception e) {

            e.printStackTrace();
        }

        return height;
    }
}
