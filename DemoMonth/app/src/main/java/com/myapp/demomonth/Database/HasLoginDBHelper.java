package com.myapp.demomonth.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.myapp.demomonth.R;

public class HasLoginDBHelper extends SQLiteOpenHelper {
    private static HasLoginDBHelper myInstance;

    public static HasLoginDBHelper getInstance(Context context) {
        if (myInstance == null) {
            myInstance = new HasLoginDBHelper(context, "hasLogin", null, R.integer.has_login_db_version);
        }
        return myInstance;
    }

    private HasLoginDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table hasLogin(account text primary key);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}