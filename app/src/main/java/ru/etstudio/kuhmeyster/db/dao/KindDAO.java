package ru.etstudio.kuhmeyster.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ru.etstudio.kuhmeyster.db.common.CursorHelper;
import ru.etstudio.kuhmeyster.db.entity.DBContract;
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
                if (cursor.moveToFirst()) {
                    do {
                        Kind.Builder builder = Kind.newBuilder()
                                .setId(cursor.getLong(cursor.getColumnIndex(Kind._ID)))
                                .setCreated(cursor.getLong(cursor.getColumnIndex(Kind.COLUMN_CREATED)))
                                .setLabel(cursor.getString(cursor.getColumnIndex(Kind.COLUMN_LABEL)));
                        kinds.add(builder.build());
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
                if (cursor.moveToFirst()) {
                    Kind.Builder builder = Kind.newBuilder()
                            .setId(id)
                            .setCreated(cursor.getLong(cursor.getColumnIndex(Kind.COLUMN_CREATED)))
                            .setLabel(cursor.getString(cursor.getColumnIndex(Kind.COLUMN_LABEL)));
                    return builder.build();
                }
            } catch (Exception e) {
                Log.e(LOG_TAG, e.getMessage());
            } finally {
                CursorHelper.closeCursor(cursor);
            }
        }

        return null;
    }

    @Override
    public long insert(Kind kind) {
        long id = exist(kind);

        if (kind == null) {
            return id;
        }

        try {
            if (db != null) {
                db.beginTransaction();
                ContentValues values = new ContentValues();
                values.put(Kind.COLUMN_CREATED, kind.getCreated().getTime());
                values.put(Kind.COLUMN_LABEL, kind.getLabel());
                if (id == -1) {
                    id = db.insert(Kind.TABLE_NAME, null, values);
                } else {
                    db.update(Kind.TABLE_NAME, values, DBContract._ID + " = ?", new String[]{String.valueOf(kind.getId())});
                }
                db.setTransactionSuccessful();
            }
        } catch (SQLiteException e) {
            Log.e(LOG_TAG, e.getMessage());
        } finally {
            if (db != null) {
                db.endTransaction();
            }
        }

        return id;
    }

    @Override
    public void update(Kind kind) {
        insert(kind);
    }
}
