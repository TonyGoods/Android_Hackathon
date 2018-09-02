package com.myapp.demomonth.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.myapp.demomonth.R;

public class AnnouncementDBHelper extends SQLiteOpenHelper {
    private static AnnouncementDBHelper myInstance;

    private AnnouncementDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static AnnouncementDBHelper getInstance(Context context) {
        if (myInstance == null) {
            myInstance = new AnnouncementDBHelper(context, "announcement", null, R.integer.announcement_db_version);
        }

        return myInstance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table announcement(" +
                "title text primary key," +
                "time text," +
                "content text);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}