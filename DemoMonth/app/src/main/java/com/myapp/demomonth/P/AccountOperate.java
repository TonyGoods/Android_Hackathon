package com.myapp.demomonth.P;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.myapp.demomonth.Database.LoginDBHelper;

public class AccountOperate {
    private static LoginDBHelper helper;

    private AccountOperate() {
    }

    public static String gotPassword(Context context, String account) {
        String password = null;

        if (helper == null) {
            helper = LoginDBHelper.getInstance(context);
        }

        SQLiteDatabase sqLiteDatabase = helper.getReadableDatabase();
        try (Cursor cursor = sqLiteDatabase.query("accounts", new String[]{"password"}, "account=?", new String[]{account}, null, null, null)) {
            while (cursor.moveToNext()) {
                password = cursor.getString(0);
            }
        }
        return password;
    }

    @SuppressLint("Recycle")
    public static boolean login(Context context, String account, String password) {
        if (helper == null) {
            helper = LoginDBHelper.getInstance(context);
        }

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query("accounts", new String[]{"password"}, "account=?", new String[]{account}, null, null, null);
        while (cursor.moveToNext()) {
            if (cursor.getString(0).equals(password)) {
                HasLoginOperate.add(context, account);
                return true;
            }
        }
        return false;
    }

    public static boolean register(Context context, String account, String password) {
        if (helper == null) {
            helper = LoginDBHelper.getInstance(context);
        }

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("account", account);
        contentValues.put("password", password);
        if (db.insert("accounts", null, contentValues) != -1) {
            UserOperate.addUser(context, account);
            return true;
        }
        return false;
    }
}
