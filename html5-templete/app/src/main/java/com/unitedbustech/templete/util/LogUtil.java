package com.unitedbustech.templete.util;

import android.util.Log;

public class LogUtil {

    private static final String TAG = "test";

    private static final int VERBOSE = 1;

    private static final int DEBUG = 2;

    private static final int INFO = 3;

    private static final int WRAN = 4;

    private static final int ERROR = 5;

    private static final int NOTHING = 6; //修改此处控制日志输出

    public static void v(String msg) {

        if (VERBOSE <= NOTHING)
            Log.v(TAG, msg);
    }

    public static void v(String tag, String msg) {

        if (VERBOSE <= NOTHING)
            Log.v(tag, msg);
    }

    public static void i(String msg) {

        if (INFO <= NOTHING)
            Log.i(TAG, msg);
    }

    public static void i(String tag, String msg) {

        if (INFO <= NOTHING)
            Log.i(tag, msg);
    }

    public static void w(String msg) {

        if (WRAN <= NOTHING)
            Log.w(TAG, msg);
    }

    public static void w(String tag, String msg) {

        if (WRAN <= NOTHING)
            Log.w(tag, msg);
    }

    public static void d(String msg) {

        if (DEBUG <= NOTHING)
            Log.d(TAG, msg);
    }

    public static void d(String tag, String msg) {

        if (DEBUG <= NOTHING)
            Log.d(tag, msg);
    }

    public static void e(String msg) {

        if (ERROR <= NOTHING)
            Log.e(TAG, msg);
    }

    public static void e(String tag, String msg) {

        if (ERROR <= NOTHING)
            Log.e(tag, msg);
    }
}
