package com.unitedbustech.templete.database;

import android.database.Cursor;
import android.text.TextUtils;

import com.unitedbustech.templete.model.DaoMaster;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.internal.DaoConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yufei0213
 */
public class MigrationHelper {

    public static void migrate(Database db, Class<? extends AbstractDao<?, ?>>... daoClasses) {

        generateTempTables(db, daoClasses);

        DaoMaster.dropAllTables(db, true);
        DaoMaster.createAllTables(db, false);

        restoreData(db, daoClasses);
    }

    private static void generateTempTables(Database db, Class<? extends AbstractDao<?, ?>>... daoClasses) {

        for (int i = 0; i < daoClasses.length; i++) {

            DaoConfig daoConfig = new DaoConfig(db, daoClasses[i]);

            if (!checkTable(db, daoConfig.tablename))
                continue;

            String divider = "";
            String tableName = daoConfig.tablename;
            String tempTableName = daoConfig.tablename.concat("_TEMP");
            ArrayList<String> properties = new ArrayList<>();

            StringBuilder createTableStringBuilder = new StringBuilder();

            createTableStringBuilder.append("CREATE TABLE ").append(tempTableName).append(" (");

            for (int j = 0; j < daoConfig.properties.length; j++) {

                String columnName = daoConfig.properties[j].columnName;

                if (getColumns(db, tableName).contains(columnName)) {

                    properties.add(columnName);

                    String type = null;

                    try {

                        type = getTypeByClass(daoConfig.properties[j].type);
                    } catch (Exception e) {

                        e.printStackTrace();
                    }

                    createTableStringBuilder.append(divider).append(columnName).append(" ").append(type);

                    if (daoConfig.properties[j].primaryKey) {

                        createTableStringBuilder.append(" PRIMARY KEY");
                    }

                    divider = ",";
                }
            }
            createTableStringBuilder.append(");");

            db.execSQL(createTableStringBuilder.toString());

            StringBuilder insertTableStringBuilder = new StringBuilder();

            insertTableStringBuilder.append("INSERT INTO ").append(tempTableName).append(" (");
            insertTableStringBuilder.append(TextUtils.join(",", properties));
            insertTableStringBuilder.append(") SELECT ");
            insertTableStringBuilder.append(TextUtils.join(",", properties));
            insertTableStringBuilder.append(" FROM ").append(tableName).append(";");

            db.execSQL(insertTableStringBuilder.toString());
        }
    }

    private static void restoreData(Database db, Class<? extends AbstractDao<?, ?>>... daoClasses) {

        for (int i = 0; i < daoClasses.length; i++) {

            DaoConfig daoConfig = new DaoConfig(db, daoClasses[i]);

            String tableName = daoConfig.tablename;
            String tempTableName = daoConfig.tablename.concat("_TEMP");

            if (!checkTable(db, tempTableName))
                continue;

            ArrayList<String> properties = new ArrayList();

            for (int j = 0; j < daoConfig.properties.length; j++) {

                String columnName = daoConfig.properties[j].columnName;

                if (getColumns(db, tempTableName).contains(columnName)) {

                    properties.add(columnName);
                }
            }

            StringBuilder insertTableStringBuilder = new StringBuilder();

            insertTableStringBuilder.append("INSERT INTO ").append(tableName).append(" (");
            insertTableStringBuilder.append(TextUtils.join(",", properties));
            insertTableStringBuilder.append(") SELECT ");
            insertTableStringBuilder.append(TextUtils.join(",", properties));
            insertTableStringBuilder.append(" FROM ").append(tempTableName).append(";");

            StringBuilder dropTableStringBuilder = new StringBuilder();

            dropTableStringBuilder.append("DROP TABLE ").append(tempTableName);

            db.execSQL(insertTableStringBuilder.toString());
            db.execSQL(dropTableStringBuilder.toString());
        }
    }

    /**
     * 获取表每列数据的数据类型
     *
     * @param type
     * @return
     * @throws Exception
     */
    private static String getTypeByClass(Class<?> type) throws Exception {

        if (type.equals(String.class)) {

            return "TEXT";
        }

        if (type.equals(Long.class) || type.equals(Integer.class) || type.equals(long.class)) {

            return "INTEGER";
        }

        if (type.equals(Boolean.class)) {

            return "BOOLEAN";
        }

        throw new Exception();
    }

    /**
     * 获取表的每一列
     *
     * @param db
     * @param tableName
     * @return
     */
    private static List<String> getColumns(Database db, String tableName) {

        List<String> columns = new ArrayList<>();
        Cursor cursor = null;

        try {

            cursor = db.rawQuery("SELECT * FROM " + tableName + " limit 1", null);
            if (cursor != null) {

                columns = new ArrayList<>(Arrays.asList(cursor.getColumnNames()));
            }
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            if (cursor != null)
                cursor.close();
        }

        return columns;
    }

    /**
     * 检测table是否存在
     *
     * @param db
     * @param tableName
     */
    private static Boolean checkTable(Database db, String tableName) {

        StringBuilder query = new StringBuilder();
        query.append("SELECT count(*) FROM sqlite_master WHERE type='table' AND name='").append(tableName).append("'");

        Cursor c = db.rawQuery(query.toString(), null);
        if (c.moveToNext()) {

            int count = c.getInt(0);
            if (count > 0) {

                return true;
            }

            return false;
        }

        return false;
    }
}
