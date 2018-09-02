package com.myapp.demomonth.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.myapp.demomonth.R;

public class MessageDBHelper extends SQLiteOpenHelper {
    private static MessageDBHelper myInstance;

    public static MessageDBHelper getInstance(Context context) {
        if (myInstance == null) {
            myInstance = new MessageDBHelper(context, "message", null, R.integer.message_db_version);
        }
        return myInstance;
    }

    private MessageDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table message(" +
                "id Integer primary key AUTOINCREMENT," +
                "sender text," +
                "receiver text," +
                "time text," +
                "content text);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}