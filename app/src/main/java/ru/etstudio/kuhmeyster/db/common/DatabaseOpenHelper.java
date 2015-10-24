package ru.etstudio.kuhmeyster.db.common;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import ru.etstudio.kuhmeyster.db.entity.CmpIngredient;
import ru.etstudio.kuhmeyster.db.entity.Dish;
import ru.etstudio.kuhmeyster.db.entity.Ingredient;
import ru.etstudio.kuhmeyster.db.entity.Kind;
import ru.etstudio.kuhmeyster.db.entity.Recd;
import ru.etstudio.kuhmeyster.db.scripts.SetupInitialData;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG = DatabaseOpenHelper.class.getName();

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "kuhmeyster.sqlite";

    private static volatile DatabaseOpenHelper instance;

    protected Context context;

    protected DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public static synchronized DatabaseOpenHelper instance(Context context) {
        DatabaseOpenHelper localInstance = instance;
        if (localInstance == null) {
            synchronized (DatabaseOpenHelper.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DatabaseOpenHelper(context);
                }
            }
        }

        return localInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Kind.SQL_CREATE_TABLE);
        Log.i(LOG_TAG, "KIND table created");
        db.execSQL(Dish.SQL_CREATE_TABLE);
        Log.i(LOG_TAG, "DISH table created");
        db.execSQL(Recd.SQL_CREATE_TABLE);
        Log.i(LOG_TAG, "RECD table created");
        db.execSQL(Ingredient.SQL_CREATE_TABLE);
        Log.i(LOG_TAG, "INGREDIENT table created");
        db.execSQL(CmpIngredient.SQL_CREATE_TABLE);
        Log.i(LOG_TAG, "CMP_INGREDIENT table created");

        SetupInitialData initialData = new SetupInitialData(context, db);
        initialData.fillInitialKind();
        initialData.fillInitialIngredient();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.delete(Kind.TABLE_NAME, null, null);
        SetupInitialData initialData = new SetupInitialData(context, db);
        initialData.fillInitialKind();
    }
}
