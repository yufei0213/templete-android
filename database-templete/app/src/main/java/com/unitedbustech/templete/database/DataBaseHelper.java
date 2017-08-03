package com.unitedbustech.templete.database;

import android.database.sqlite.SQLiteDatabase;

import com.unitedbustech.templete.App;
import com.unitedbustech.templete.model.DaoMaster;
import com.unitedbustech.templete.model.DaoSession;

public class DataBaseHelper {

    private static final String DATA_BASE_NAME = "templete-database";

    private DaoSession daoSession;

    private DataBaseHelper() {

        DaoMaster.OpenHelper helper = new DataBaseOpenHelper(App.getContext(), DATA_BASE_NAME);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    private static class DataBaseHelperHolder {

        private static final DataBaseHelper INSTANCE = new DataBaseHelper();
    }

    public static DataBaseHelper getInstance() {

        return DataBaseHelperHolder.INSTANCE;
    }

    public DaoSession getDaoSession() {

        return this.daoSession;
    }
}
