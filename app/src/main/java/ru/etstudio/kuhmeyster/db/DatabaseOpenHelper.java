package ru.etstudio.kuhmeyster.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "kuhmeyster.sqlite";

    private SQLiteDatabase db;

    private DatabaseHelper dh;

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DatabaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Kind.SQL_CREATE_TABLE);
        db.execSQL(Dish.SQL_CREATE_TABLE);
        db.execSQL(Recd.SQL_CREATE_TABLE);
        db.execSQL(Ingredient.SQL_CREATE_TABLE);
        db.execSQL(CmpIngredient.SQL_CREATE_TABLE);
        insertInitialKindData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public DatabaseHelper open() {
        if (!isOpen()) {
            db = this.getWritableDatabase();
            dh = new DatabaseHelper(db);
        }
        return dh;
    }

    public boolean isOpen() {
        return db != null && db.isOpen();
    }

    private void insertInitialKindData(SQLiteDatabase db) {
        if (db == null) return;

        for (String title : Kind.initialKinds) {
            ContentValues values = new ContentValues();
            values.put(Kind.Entry.COLUMN_KIND, title);
            db.insert(Kind.Entry.TABLE_NAME, null, values);
        }
    }
}
