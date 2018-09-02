package com.myapp.demomonth.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.myapp.demomonth.R;

public class UserDBHelper extends SQLiteOpenHelper {
    private static UserDBHelper myInstance;

    public static UserDBHelper getInstance(Context context) {
        if (myInstance == null) {
            myInstance = new UserDBHelper(context, "user", null, R.integer.user_db_version);
        }

        return myInstance;
    }

    private UserDBHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table user(" +
                "account text primary key," +
                "username text," +
                "sex text," +
                "age text," +
                "country text," +
                "phone text);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}