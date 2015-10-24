package ru.etstudio.kuhmeyster.db.scripts;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import ru.etstudio.kuhmeyster.R;
import ru.etstudio.kuhmeyster.db.entity.Ingredient;
import ru.etstudio.kuhmeyster.db.entity.Kind;

public class SetupInitialData {

    private static final String LOG_TAG = SetupInitialData.class.getName();

    private final Context context;

    private final SQLiteDatabase db;

    public SetupInitialData(Context context, SQLiteDatabase db) {
        this.context = context;
        this.db = db;
    }

    public void fillInitialKind() {
        if (db != null) {
            Resources resources = context.getResources();
            List<String> kinds = Arrays.asList(resources.getStringArray(R.array.kinds));

            try {
                db.beginTransaction();
                for (String kind : kinds) {
                    ContentValues values = new ContentValues();
                    values.put(Kind.COLUMN_LABEL, kind);
                    values.put(Kind.COLUMN_CREATED, new Date().getTime());
                    db.insert(Kind.TABLE_NAME, null, values);
                    Log.d(LOG_TAG, "insert to kind: " + kind);
                }
                db.setTransactionSuccessful();

                Log.i(LOG_TAG, "Kind table filled");
            } catch (SQLiteException e) {
                Log.e(LOG_TAG, e.getMessage());
            } finally {
                db.endTransaction();
            }
        }
    }

    public void fillInitialIngredient() {
        if (db != null) {
            Resources resources = context.getResources();
            List<String> ingredients = Arrays.asList(resources.getStringArray(R.array.ingredients));

            try {
                db.beginTransaction();
                for (String ingredient : ingredients) {
                    ContentValues values = new ContentValues();
                    values.put(Ingredient.COLUMN_TITLE, ingredient);
                    db.insert(Ingredient.TABLE_NAME, null, values);
                    Log.d(LOG_TAG, "insert to ingredient: " + ingredient);
                }
                db.setTransactionSuccessful();

                Log.i(LOG_TAG, "Ingredient table filled");
            } catch (SQLiteException e) {
                Log.e(LOG_TAG, e.getMessage());
            } finally {
                db.endTransaction();
            }
        }
    }

    private void fillInitialDish(SQLiteDatabase db) {
        //TODO дополнительно заполнить таблицу соединений блюд и ингредиентов
    }
}
