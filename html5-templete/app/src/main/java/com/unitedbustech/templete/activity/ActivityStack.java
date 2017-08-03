package com.unitedbustech.templete.activity;

import android.app.Activity;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * @author yufei0213
 */
public class ActivityStack {

    private LinkedList<Activity> activityList;

    private ActivityStack() {

        activityList = new LinkedList<>();
    }

    private static class ActivityStackHolder {

        private static final ActivityStack INSTANCE = new ActivityStack();
    }

    public static ActivityStack getInstance() {

        return ActivityStackHolder.INSTANCE;
    }

    public Activity getCurrentActivity() {

        Activity activity = null;

        try {

            activity = activityList.getLast();
        } catch (NoSuchElementException e) {

            e.printStackTrace();
        }

        return activity;
    }

    public void addActivity(Activity activity) {

        activityList.push(activity);
    }

    public void removeActivity(Activity activity) {

        activityList.remove(activity);
    }

    public void finishActivity(Class<?> activityClass) {

        for (Activity activity : activityList) {

            if (activity.getClass().equals(activityClass)) {

                activity.finish();
                removeActivity(activity);

                break;
            }
        }
    }

    public void finishOthers(Class<?> activityClass) {

        LinkedList<Activity> delActivities = new LinkedList<>();

        for (Activity activity : activityList) {

            if (!activity.getClass().equals(activityClass)) {

                activity.finish();
                delActivities.add(activity);
            }
        }

        activityList.removeAll(delActivities);
    }

    public void finishAll() {

        for (Activity activity : activityList) {

            activity.finish();
        }

        activityList.clear();
    }
}
