package com.unitedbustech.templete.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yufei0213
 */
public class JsonUtil {

    public static String getString(JSONObject obj, String text) {

        String result = "";

        try {

            result = obj.getString(text);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return result;
    }

    public static int getInt(JSONObject obj, String text) {

        int result = 0;

        try {

            result = obj.getInteger(text);
        } catch (Exception e) {

            e.printStackTrace();
        }

        return result;
    }

    public static float getFloat(JSONObject obj, String text) {

        float result = 0f;

        try {

            result = obj.getFloat(text);
        } catch (Exception e) {

            e.printStackTrace();
        }

        return result;
    }

    public static double getDouble(JSONObject obj, String text) {

        double result = 0d;

        try {

            result = obj.getDouble(text);
        } catch (Exception e) {

            e.printStackTrace();
        }

        return result;
    }

    public static boolean getBoolean(JSONObject obj, String text) {

        boolean result = false;

        try {

            result = obj.getBoolean(text);
        } catch (Exception e) {

            e.printStackTrace();
        }

        return result;
    }

    public static JSONObject getJsonObject(JSONObject obj, String text) {

        JSONObject object = null;

        try {

            object = obj.getJSONObject(text);
        } catch (Exception e) {

            e.printStackTrace();
        }

        return object;
    }

    public static JSONObject parseObject(String text) {

        JSONObject object = null;

        try {

            object = JSON.parseObject(text);
        } catch (Exception e) {

            e.printStackTrace();
        }

        return object;
    }

    public static <T> T parseObject(String text, Class<T> classz) {

        try {

            return JSON.parseObject(text, classz);
        } catch (Exception e) {

            return null;
        }
    }

    public static <T> List<T> parseArray(String text, Class<T> clazz) {

        List<T> list = new ArrayList<>();

        try {

            list = JSON.parseArray(text, clazz);
        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }
}
