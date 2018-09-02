package com.myapp.demomonth.P;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.myapp.demomonth.Database.MessageDBHelper;
import com.myapp.demomonth.Model.Message;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageOperate {

    @SuppressLint("SimpleDateFormat")
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static MessageDBHelper helper;

    private MessageOperate() {
    }

    public static List<Message> getMessages(Context context, String account, String friendAccount) {
        List<Message> messages = new ArrayList<>();
        if (helper == null) {
            helper = MessageDBHelper.getInstance(context);
        }

        SQLiteDatabase db = helper.getReadableDatabase();
        try (Cursor cursor = db.query("message", new String[]{"sender", "receiver", "time", "content"},
                "(sender = ? and receiver = ?) or (sender = ? and receiver = ?)", new String[]{account, friendAccount, friendAccount, account},
                null, null, null)) {
            while (cursor.moveToNext()) {
                messages.add(new Message(cursor.getString(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3)));
            }
        }
        return messages;
    }

    public static void sendMessage(Context context, String account, String friendAccount, String message) {
        if (helper == null) {
            helper = MessageDBHelper.getInstance(context);
        }

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("sender", account);
        contentValues.put("receiver", friendAccount);
        contentValues.put("content", message);
        contentValues.put("time", dateFormat.format(new Date()));
        db.insert("message", null, contentValues);
    }

    @SuppressLint("Recycle")
    public static Message getLastMessage(Context context, String account, String friendAccount) {
        if (helper == null) {
            helper = MessageDBHelper.getInstance(context);
        }

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(
                "message",
                new String[]{"sender", "receiver", "time", "content"},
                "(sender = ? and receiver = ?) or (sender = ? and receiver = ?)",
                new String[]{account, friendAccount, friendAccount, account},
                null, null, null);

        if (cursor.moveToLast()) {
            return new Message(cursor.getString(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3));
        }

        return null;
    }
}