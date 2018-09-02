package com.myapp.demomonth.P;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.myapp.demomonth.Database.UserDBHelper;
import com.myapp.demomonth.Model.Friend;
import com.myapp.demomonth.Model.User;

import java.util.ArrayList;
import java.util.List;

public class UserOperate {
    private static UserDBHelper helper;

    private UserOperate() {
    }

    public static void addUser(Context context, String account) {
        if (helper == null) {
            helper = UserDBHelper.getInstance(context);
        }

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("account", account);
        contentValues.put("username", account);
        db.insert("user", null, contentValues);
        db.close();
    }

    public static void modifyUser(Context context, String account, String column, String value) {
        if (helper == null) {
            helper = UserDBHelper.getInstance(context);
        }

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(column, value);
        db.update("user", contentValues, "account=?", new String[]{account});
    }

    public static User queryUser(Context context, String account) {
        User user = null;
        if (helper == null) {
            helper = UserDBHelper.getInstance(context);
        }

        SQLiteDatabase db = helper.getReadableDatabase();
        try (Cursor cursor = db.query("user", new String[]{"username", "sex", "age", "country", "phone"},
                "account=?", new String[]{account}, null, null, null)) {
            while (cursor.moveToNext()) {
                user = new User(account, cursor.getString(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3), cursor.getString(4));
            }
        }
        return user;
    }

    public static List<Friend> getAll(Context context, String account) {
        List<Friend> friends = new ArrayList<>();
        if (helper == null) {
            helper = UserDBHelper.getInstance(context);
        }

        SQLiteDatabase db = helper.getReadableDatabase();
        try (Cursor cursor = db.query("user", new String[]{"account", "username"}, "account!=?",
                new String[]{account}, null, null, "username")) {
            while (cursor.moveToNext()) {
                friends.add(new Friend(cursor.getString(0), cursor.getString(1)));
            }
        }
        return friends;
    }
}