package com.curs.pau.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by pau on 30/01/15.
 */
public class RestaurantORMHelper extends OrmLiteSqliteOpenHelper{
    private static final int DATABASE_VERSION = 1;

    private Dao<RestaurantORMDao, Integer> restaurantDao;

    public RestaurantORMHelper(Context context) {
        super(context, "RESTAURANTS_ORM.db", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            Log.i("RestaurantORMHelper", "onCreate");
            TableUtils.createTable(connectionSource, RestaurantORMDao.class);
        }
        catch (SQLException e) {
            Log.e("ORM Error", "onCreate Error");
            throw new RuntimeException();
        }
    }
}
