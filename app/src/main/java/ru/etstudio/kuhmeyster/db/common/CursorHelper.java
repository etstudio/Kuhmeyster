package ru.etstudio.kuhmeyster.db.common;

import android.database.Cursor;

public class CursorHelper {
    public static void closeCursor(Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }
}
