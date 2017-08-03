package com.unitedbustech.templete.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeUtils {

    private static final SimpleDateFormat usFormatter = new SimpleDateFormat("MM/dd/yyyy",
            Locale.US);
    private static final SimpleDateFormat usLongFormatter = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss", Locale.US);
    private static final SimpleDateFormat longTimeFormatter = new SimpleDateFormat(
            "yyyyMMddHHmmss", Locale.US);
    private static final SimpleDateFormat usShortFormatter = new SimpleDateFormat(
            "yyyy-MM-dd hh:mm aa", Locale.US);
    private static final SimpleDateFormat usWeekFormatter = new SimpleDateFormat("EEE", Locale.US);
    private static final SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss", Locale.US);
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    private static final SimpleDateFormat usTimeFormatter = new SimpleDateFormat("hh:mm aa", Locale.US);

    public TimeUtils() {

    }

    public static String dateToStr(Date date) {

        return usShortFormatter.format(date);
    }

    public static String dateToStrlong(Date date) {

        return usLongFormatter.format(date);
    }

    public static String dateToStrUs(Date dateDate) {

        return usFormatter.format(dateDate);
    }

    public static String dateToStrNoTime(Date dateDate) {

        return dateFormatter.format(dateDate);
    }

    public static String dateToUsTime(Date dateDate) {

        return usTimeFormatter.format(dateDate);
    }

    public static String dateToLongTime(Date dateDate) {

        return longTimeFormatter.format(dateDate);
    }

    public static Date getDateNoTime(Date date) {

        return strUsToDate(dateToStrUs(date));
    }

    public static Date getDate(long time) {

        return new Date(time);
    }

    public static String getWeekEn(Date date) {

        return usWeekFormatter.format(date);
    }

    public static Date strUsToDate(String strDate) {

        ParsePosition pos = new ParsePosition(0);
        Date strtodate = usFormatter.parse(strDate, pos);
        return strtodate;
    }
}
