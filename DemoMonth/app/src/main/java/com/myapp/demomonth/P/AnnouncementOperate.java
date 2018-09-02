package com.myapp.demomonth.P;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.myapp.demomonth.Database.AnnouncementDBHelper;
import com.myapp.demomonth.Model.Announcement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AnnouncementOperate {
    @SuppressLint("SimpleDateFormat")
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static AnnouncementDBHelper helper;


    private AnnouncementOperate() {
    }

    public static boolean make(Context context, String title, String content) {
        if (helper == null) {
            helper = AnnouncementDBHelper.getInstance(context);
        }

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("time", dateFormat.format(new Date()));
        contentValues.put("content", content);
        return db.insert("announcement", null, contentValues) != -1;
    }

    public static List<Announcement> getAll(Context context) {
        List<Announcement> announcements = new ArrayList<>();
        if (helper == null) {
            helper = AnnouncementDBHelper.getInstance(context);
        }

        SQLiteDatabase db = helper.getReadableDatabase();
        try (Cursor cursor = db.query("announcement", new String[]{"title", "time", "content"}, null, null, null, null, null)) {
            while (cursor.moveToNext()) {
                announcements.add(new Announcement(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
            }
        }
        return announcements;
    }

    public static Announcement get(Context context, String title) {
        if (helper == null) {
            helper = AnnouncementDBHelper.getInstance(context);
        }

        SQLiteDatabase db = helper.getReadableDatabase();
        try (Cursor cursor = db.query("announcement", new String[]{"title", "time", "content"}, "title=?", new String[]{title}, null, null, null)) {
            cursor.moveToNext();
            return new Announcement(cursor.getString(0), cursor.getString(1), cursor.getString(2));
        }
    }

    public static void delete(Context context, String title) {
        if (helper == null) {
            helper = AnnouncementDBHelper.getInstance(context);
        }

        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete("announcement", "title = ?", new String[]{title});
    }
}