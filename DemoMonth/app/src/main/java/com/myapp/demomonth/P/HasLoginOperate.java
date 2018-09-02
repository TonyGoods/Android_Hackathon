package com.myapp.demomonth.P;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.myapp.demomonth.Database.HasLoginDBHelper;

public class HasLoginOperate {
    private static HasLoginDBHelper helper;

    private HasLoginOperate() {
    }

    public static void add(Context context, String username) {
        if (helper == null) {
            helper = HasLoginDBHelper.getInstance(context);
        }

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("account", username);
        db.insert("hasLogin", null, contentValues);
        db.close();
    }

    public static String query(Context context) {
        String account = null;
        if (helper == null) {
            helper = HasLoginDBHelper.getInstance(context);
        }

        SQLiteDatabase db = helper.getReadableDatabase();
        try (Cursor cursor = db.query("hasLogin", new String[]{"account"}, null, null, null, null, null)) {
            while (cursor.moveToNext()) {
                account = cursor.getString(0);
            }
        }
        return account;
    }

    public static void delete(Context context) {
        if (helper == null) {
            helper = HasLoginDBHelper.getInstance(context);
        }

        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete("hasLogin", null, null);
    }
}