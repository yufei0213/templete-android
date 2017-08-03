package com.unitedbustech.templete.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import java.lang.reflect.Method;

/**
 * @author yufei0213
 */
public class AppUtil {

    public static String getAppName(Context context) {

        PackageManager packageManager = context.getPackageManager();
        try {

            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;

            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e) {

            e.printStackTrace();
        }

        return null;
    }

    public static String getVersionName(Context context) {

        String versionName = null;

        try {

            PackageManager pm = context.getPackageManager();
            PackageInfo info = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {

            e.printStackTrace();
        }

        return versionName;
    }

    public static int getVersionCode(Context context) {

        int versionCode = 0;

        try {

            PackageManager pm = context.getPackageManager();
            PackageInfo info = pm.getPackageInfo(context.getPackageName(), 0);
            versionCode = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {

            e.printStackTrace();
        }

        return versionCode;
    }

    public static boolean checkPermission(Context context, String permission) {

        boolean result = false;

        if (Build.VERSION.SDK_INT >= 23) {

            try {

                Class clazz = Class.forName("android.content.Context");
                Method method = clazz.getMethod("checkSelfPermission", String.class);
                int rest = (Integer) method.invoke(context, permission);

                if (rest == PackageManager.PERMISSION_GRANTED)
                    result = true;
                else
                    result = false;

            } catch (Exception e) {

                result = false;
            }
        } else {

            PackageManager pm = context.getPackageManager();

            if (pm.checkPermission(permission, context.getPackageName()) == PackageManager.PERMISSION_GRANTED)
                result = true;
        }

        return result;
    }

    public static Intent getAppSettingIntent(Context context) {

        Uri packageURI = Uri.parse("package:" + context.getPackageName());
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);

        return intent;
    }
}
