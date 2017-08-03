package com.unitedbustech.templete.config;

/**
 * @author yufei0213
 */
public class Constants {

    /**
     * sd卡绝对路径
     */
    private static final String sdDir = "/storage/emulated/0/";

    /**
     * app数据存储路径
     */
    public static final String STORAGE_PATH = sdDir + "com.unitedbustech.megatrac.charterbus/";

    /**
     * 下载缓存路径
     */
    public static final String DOWNLOAD_PATH = STORAGE_PATH + "download/";

    /**
     * 安装包存放路径
     */
    public static final String APK_PATH = STORAGE_PATH + "apk/";

    /**
     * 静态文件存放目录
     */
    public final static String STATIC_FILE_ROOT = "file:///android_asset/";
}
