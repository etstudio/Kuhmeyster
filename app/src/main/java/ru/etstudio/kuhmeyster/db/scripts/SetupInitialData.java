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
import ru.etstudio.kuhmeyster.db.entity.Dish;
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

    public void fillInitialDish() {
        String cooking = "Салатные листья замочить в холодной воде на 1 час, чтобы они стали свежими и хрустящими.\n" +
                "Белый хлеб очистить от корочки и порезать на кубики размером примерно 1 сантиметр, затем выложить на противень и подсушить в не слишком горячей духовке.\n" +
                "В глубокую сковороду налить растительное масло, положить измельченный чеснок. Как только кусочки потемнеют, снять их со сковороды и выложить в масло сухарики. Обжарить до золотистой корочки, выложить на бумажную салфетку для удаления лишнего масла.\n" +
                "Куриное филе натереть солью и обжарить до готовности, затем остудить и порезать тонкими пластинками.\n" +
                "Листья салата порвать руками, сыр нарезать тонкими пластинками. Помидоры черри разрезать на четыре части.\n" +
                "Выложить в салатник все ингредиенты, слегка встряхнуть, чтобы они перемешались, и сразу же подать на стол. Майонез подать отдельно, чтобы каждый едок мог добавлять его по вкусу.";
        if (db != null) {
            try {
                db.beginTransaction();
                ContentValues values = new ContentValues();
                values.put(Dish.COLUMN_TITLE, "Салат \"Цезарь\" с курицей и сухариками");
                values.put(Dish.COLUMN_COOKING, cooking);
                values.put(Dish.COLUMN_CELEBRATORY, true);
                values.put(Dish.COLUMN_KIND_ID, 3);
                db.insert(Dish.TABLE_NAME, null, values);
                db.setTransactionSuccessful();

                Log.i(LOG_TAG, "Dish table filled");
            } catch (SQLiteException e) {
                Log.e(LOG_TAG, e.getMessage());
            } finally {
                db.endTransaction();
            }
        }
    }
}
