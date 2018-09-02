package com.myapp.demomonth.P;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

public class GetContacts {
    private GetContacts() {
    }

    public static String[] get(Context context) {
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        String[] projection = new String[]{
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.SORT_KEY_PRIMARY
        };

        String[] arr;
        try (Cursor cursor = context.getContentResolver().query(uri, projection, null, null, ContactsContract.Contacts.SORT_KEY_PRIMARY)) {
            arr = new String[0];
            if (cursor != null) {
                arr = new String[cursor.getCount()];
            }
            int i = 0;
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    Long id = cursor.getLong(0);
                    arr[i] = cursor.getString(1);
                    String[] phoneProjection = new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER};

                    try (Cursor phonesCusor = context.getContentResolver().query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            phoneProjection,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + id,
                            null,
                            null)) {
                        if (phonesCusor != null && phonesCusor.moveToFirst()) {
                            String num = phonesCusor.getString(0);
                            arr[i] += "," + num.replace(" ","");
                        }
                    }
                    i++;
                } while (cursor.moveToNext());
            }
        }
        return arr;
    }
}