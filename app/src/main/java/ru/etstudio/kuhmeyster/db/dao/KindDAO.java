package ru.etstudio.kuhmeyster.db.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.etstudio.kuhmeyster.db.common.CursorHelper;
import ru.etstudio.kuhmeyster.db.entity.Kind;

public class KindDAO extends DAO<Kind> {

    private static final String LOG_TAG = KindDAO.class.getName();

    public KindDAO(Context context) {
        super(context);
    }

    @Override
    public List<Kind> getAll() {
        List<Kind> kinds = new ArrayList<>();
        if (db != null) {
            Cursor cursor = null;
            try {
                cursor = db.query(Kind.TABLE_NAME, null, null, null, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    do {
                        long id = cursor.getLong(cursor.getColumnIndex(Kind._ID));
                        Date created = new Date(cursor.getLong(cursor.getColumnIndex(Kind.COLUMN_CREATED)));
                        String title = cursor.getString(cursor.getColumnIndex(Kind.COLUMN_TITLE));
                        kinds.add(new Kind(id, title, created));
                    } while (cursor.moveToNext());
                }
            } catch (SQLiteException e) {
                Log.e(LOG_TAG, e.getMessage());
            } finally {
                CursorHelper.closeCursor(cursor);
            }
        }
        return kinds;
    }

    @Override
    public Kind get(long id) {
        String query = "select * from %s where %s = %s";

        if (db != null) {
            Cursor cursor = null;
            try {
                cursor = db.rawQuery(String.format(query, Kind.TABLE_NAME, Kind._ID, id), null);
                if (cursor != null && cursor.moveToFirst()) {
                    String title = cursor.getString(cursor.getColumnIndex(Kind.COLUMN_TITLE));
                    Date created = new Date(cursor.getLong(cursor.getColumnIndex(Kind.COLUMN_CREATED)));
                    return new Kind(id, title, created);
                }
            } catch (SQLiteException e) {
                Log.e(LOG_TAG, e.getMessage());
            } finally {
                CursorHelper.closeCursor(cursor);
            }
        }

        return null;
    }

    @Override
    public void update(Kind kind) {
        super.insert(kind);
    }
}
