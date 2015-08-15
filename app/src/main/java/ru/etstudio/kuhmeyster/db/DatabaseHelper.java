package ru.etstudio.kuhmeyster.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DatabaseHelper {

    private static final String LOG_TAG = DatabaseHelper.class.getName();

    private SQLiteDatabase db;

    public DatabaseHelper(SQLiteDatabase db) {
        this.db = db;
    }

    public void updateKind(int kindId) {
        db.execSQL("update kind set kind = 'ПРИВЕТ' where _id = 5");
    }

    public long addDish(Dish dish) {
        checkState();

        ContentValues values = new ContentValues();
        values.put(Dish.Entry.COLUMN_KIND_ID, dish.getKindId());
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
        if (db == null || !db.isOpen()) {
            throw new IllegalStateException("The database has not been opened");
        }
    }
}
