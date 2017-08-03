package com.unitedbustech.templete.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.unitedbustech.templete.model.DaoMaster;
import com.unitedbustech.templete.model.UserDao;

import org.greenrobot.greendao.database.Database;

/**
 * @author yufei0213
 */
public class DataBaseOpenHelper extends DaoMaster.OpenHelper {

    public DataBaseOpenHelper(Context context, String name) {
        this(context, name, null);
    }

    public DataBaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {

        MigrationHelper.migrate(db, UserDao.class);
    }
}
