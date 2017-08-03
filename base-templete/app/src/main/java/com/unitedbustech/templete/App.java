package com.unitedbustech.templete;

import android.app.Application;
import android.content.Context;

import com.unitedbustech.templete.excetion.CrashHandler;

/**
 * @author yufei0213
 */
public class App extends Application {

    private static Context context;

    @Override
    public void onCreate() {

        super.onCreate();

        context = getApplicationContext();

        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
    }

    public static Context getContext() {

        return context;
    }
}
