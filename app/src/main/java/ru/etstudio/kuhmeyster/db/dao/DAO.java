package ru.etstudio.kuhmeyster.db.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.List;

import ru.etstudio.kuhmeyster.db.common.CursorHelper;
import ru.etstudio.kuhmeyster.db.common.DatabaseOpenHelper;
import ru.etstudio.kuhmeyster.db.entity.DBContract;

public abstract class DAO<T extends DBContract> {

    private static final String LOG_TAG = DAO.class.getName();

    protected final DatabaseOpenHelper databaseOpenHelper;

    protected final SQLiteDatabase db;

    public DAO(Context context) {
        databaseOpenHelper = DatabaseOpenHelper.instance(context);
        db = databaseOpenHelper.getWritableDatabase();
    }

    public abstract List<T> getAll();

    public abstract T get(long id);

    public abstract long insert(T object);

    public abstract void update(T object);

    public void delete(T object) {
        if (object == null) {
            return;
        }

        try {
            if (db != null) {
                String tableName = getTableName(object);

                db.beginTransaction();
                db.delete(tableName, DBContract._ID + " = ?", new String[]{String.valueOf(object.getId())});
                db.setTransactionSuccessful();
            }
        } catch (SQLiteException | NoSuchFieldException | IllegalAccessException e) {
            Log.e(LOG_TAG, e.getMessage());
        } finally {
            if (db != null) {
                db.endTransaction();
            }
        }
    }

    public void close() {
        if (databaseOpenHelper != null) {
            databaseOpenHelper.close();
        }
    }

    protected long exist(T object) {
        if (object == null) {
            return -1;
        }

        String query = "select _id from %s where %s = %s";

        if (db != null) {
            Cursor cursor = null;
            try {
                String tableName = getTableName(object);
                cursor = db.rawQuery(String.format(query, tableName, DBContract._ID, object.getId()), null);
                if (cursor != null && cursor.moveToFirst()) {
                    return cursor.getLong(cursor.getColumnIndex(DBContract._ID));
                }
            } catch (SQLiteException | NoSuchFieldException | IllegalAccessException e) {
                Log.e(LOG_TAG, e.getMessage());
            } finally {
                CursorHelper.closeCursor(cursor);
            }
        }

        return -1;
    }

    protected int getCount(String query) {
        Cursor countCursor = null;
        try {
            if (db != null) {
                countCursor = db.rawQuery(query, null);
                countCursor.moveToFirst();
                return countCursor.getInt(0);
            }
        } catch (SQLiteException e) {
            Log.e(LOG_TAG, e.getMessage());
        } finally {
            CursorHelper.closeCursor(countCursor);
        }
        return 0;
    }

    private String getTableName(T object) throws NoSuchFieldException, IllegalAccessException {
        return object.getClass().getDeclaredField("TABLE_NAME").get(object).toString();
    }
}
