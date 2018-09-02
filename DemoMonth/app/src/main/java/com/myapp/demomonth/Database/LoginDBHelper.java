package com.myapp.demomonth.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.myapp.demomonth.R;

public class LoginDBHelper extends SQLiteOpenHelper {
    private static LoginDBHelper myInstance;

    public static LoginDBHelper getInstance(Context context) {
        if (myInstance == null) {
            myInstance = new LoginDBHelper(context, "accounts", null, R.integer.login_db_version);
        }
        return myInstance;
    }

    private LoginDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table accounts(" +
                "account text primary key," +
                "password text);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}