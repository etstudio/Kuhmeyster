package ru.etstudio.kuhmeyster.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Date;

public class DatabaseHelper {

    private static final String LOG_TAG = DatabaseHelper.class.getName();

    private SQLiteDatabase db;

    private static DatabaseOpenHelper dbHelper;

    private final Context context;

    public DatabaseHelper(Context context) {
        this.context = context;
    }

    public void open() {
        dbHelper = new DatabaseOpenHelper(context);
        if (!isOpen()) {
            db = dbHelper.getWritableDatabase();
        }
    }

    public void close() {
        if (isOpen()) {
            db.close();
            if (dbHelper != null) {
                dbHelper.close();
            }
        }
    }

    public boolean isOpen() {
        return db != null && db.isOpen();
    }

    public long addDish(Dish dish) {
        checkState();

        ContentValues values = new ContentValues();
        values.put(Dish.Entry.COLUMN_KIND_ID, dish.getKindId());
        values.put(Dish.Entry.COLUMN_ADD_DATE, dish.getAdded().getTime());
        values.put(Dish.Entry.COLUMN_TITLE, dish.getTitle());
        values.put(Dish.Entry.COLUMN_COOKING, dish.getCooking());
        values.put(Dish.Entry.COLUMN_EVERYDAY, dish.isEveryday());
        values.put(Dish.Entry.COLUMN_LENTEN, dish.isLenten());
        values.put(Dish.Entry.COLUMN_CELEBRATORY, dish.isCelebratory());
        values.put(Dish.Entry.COLUMN_FISH, dish.containsFish());

        return db.insert(Dish.Entry.TABLE_NAME, null, values);
    }

    public Dish getDish(long id) {
        checkState();

        String query = "SELECT * FROM " + Dish.Entry.TABLE_NAME + " WHERE " + Dish.Entry._ID + " = " + id;
        Log.e(LOG_TAG, query);
        Dish dish = null;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            cursor.moveToFirst();
            dish = fillDish(cursor);
            cursor.close();
        }

        return dish;
    }

    //TODO cursorloader and common package
    private Dish fillDish(Cursor cursor) {
        Dish dish = new Dish();
        dish.setId(cursor.getLong(cursor.getColumnIndexOrThrow(Dish.Entry._ID)));
        dish.setKindId(cursor.getLong(cursor.getColumnIndexOrThrow(Dish.Entry.COLUMN_KIND_ID)));
        Date addDate = new Date(cursor.getLong(cursor.getColumnIndexOrThrow(Dish.Entry.COLUMN_ADD_DATE)));
        dish.setAdded(addDate);
        dish.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(Dish.Entry.COLUMN_TITLE)));
        dish.setCooking(cursor.getString(cursor.getColumnIndexOrThrow(Dish.Entry.COLUMN_COOKING)));
        boolean everyday = cursor.getInt(cursor.getColumnIndexOrThrow(Dish.Entry.COLUMN_EVERYDAY)) != 0;
        dish.setEveryday(everyday);
        boolean lenten = cursor.getInt(cursor.getColumnIndexOrThrow(Dish.Entry.COLUMN_LENTEN)) != 0;
        dish.setLenten(lenten);
        boolean celebratory = cursor.getInt(cursor.getColumnIndexOrThrow(Dish.Entry.COLUMN_CELEBRATORY)) != 0;
        dish.setCelebratory(celebratory);
        boolean containsFish = cursor.getInt(cursor.getColumnIndexOrThrow(Dish.Entry.COLUMN_FISH)) != 0;
        dish.setContainsFish(containsFish);

        return dish;
    }

    private void checkState() {
        if (db == null || !isOpen()) {
            throw new IllegalStateException("The database has not been opened");
        }
    }

    private static class DatabaseOpenHelper extends SQLiteOpenHelper {

        private static final int DATABASE_VERSION = 1;

        private static final String DATABASE_NAME = "kuhmeyster.db";

        public DatabaseOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(Kind.SQL_CREATE_TABLE_KIND);
            db.execSQL(Dish.SQL_CREATE_TABLE_DISH);

            insertInitialKindData(db);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

        private void insertInitialKindData(SQLiteDatabase db) {
            if (db == null) return;

            for (String title : Kind.initialKinds) {
                ContentValues values = new ContentValues();
                values.put(Kind.Entry.COLUMN_KIND, title);
                values.put(Kind.Entry.COLUMN_ADD_DATE, new Date().getTime());
                db.insert(Kind.Entry.TABLE_NAME, null, values);
            }
        }
    }
}
