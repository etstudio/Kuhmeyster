package ru.etstudio.kuhmeyster.db.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.lang.reflect.Field;
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

    public abstract void delete(T object);

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
            Class<?> tClass = object.getClass();
            try {
                Field tableName = tClass.getField("TABLE_NAME");
                cursor = db.rawQuery(String.format(query, tableName.getName(), DBContract._ID, object.getId()), null);
                if (cursor != null && cursor.moveToFirst()) {
                    return cursor.getLong(cursor.getColumnIndex(DBContract._ID));
                }
            } catch (SQLiteException | NoSuchFieldException e) {
                Log.e(LOG_TAG, e.getMessage());
            } finally {
                CursorHelper.closeCursor(cursor);
            }
        }

        return -1;
    }
}
