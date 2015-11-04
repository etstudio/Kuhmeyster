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
import ru.etstudio.kuhmeyster.db.entity.Dish;
import ru.etstudio.kuhmeyster.db.entity.Kind;

import static android.provider.BaseColumns._ID;

public class DishDAO extends DAO<Dish> {

    private static final String LOG_TAG = DishDAO.class.getName();

    private static final String DISH_ID = "dish_id";
    private static final String DISH_TITLE = "dish_title";
    private static final String DISH_CREATED = "dish_created";

    private StringBuffer sqlGetAll = new StringBuffer()
            .append("SELECT ")
            .append(" d.").append(_ID).append(" as ").append(DISH_ID).append(", ")
            .append(" d.").append(Dish.COLUMN_TITLE).append(" as ").append(DISH_TITLE).append(", ")
            .append(" d.").append(Dish.COLUMN_COOKING).append(", ")
            .append(" d.").append(Dish.COLUMN_CREATED).append(" as ").append(DISH_CREATED).append(", ")
            .append(" d.").append(Dish.COLUMN_CELEBRATORY).append(", ")
            .append(" d.").append(Dish.COLUMN_EVERYDAY).append(", ")
            .append(" d.").append(Dish.COLUMN_LENTEN).append(", ")
            .append(" d.").append(Dish.COLUMN_FISH).append(", ")
            .append(" d.").append(Dish.COLUMN_LAST_COOKING).append(", ")
            .append(" k.").append(_ID).append(" as kind_id, ")
            .append(" k.").append(Kind.COLUMN_LABEL).append(" as kind_label, ")
            .append(" k.").append(Kind.COLUMN_CREATED).append(" as kind_created ")
            .append("FROM ").append(Dish.TABLE_NAME).append(" as d ")
            .append("INNER JOIN ").append(Kind.TABLE_NAME).append(" as k ")
            .append("ON ")
            .append("d. ").append(Dish.COLUMN_KIND_ID).append(" = ")
            .append("k.").append(_ID);

    private StringBuffer sqlWhereId = new StringBuffer()
            .append(" where d.").append(_ID).append(" = ?");

    private StringBuffer sqlWhereKindId = new StringBuffer()
            .append(" where k.").append(_ID).append(" = ?");

    public DishDAO(Context context) {
        super(context);
    }

    @Override
    public List<Dish> getAll() {
        List<Dish> dishes = new ArrayList<>();
        if (db != null) {
            Cursor cursor = null;
            try {
                cursor = db.rawQuery(sqlGetAll.toString(), null);
                if (cursor.moveToFirst()) {
                    do {
                        Dish dish = createDish(cursor);
                        if (dish != null) {
                            dishes.add(dish);
                        }
                    } while (cursor.moveToNext());
                }
            } catch (SQLiteException e) {
                Log.e(LOG_TAG, e.getMessage());
            } finally {
                CursorHelper.closeCursor(cursor);
            }
        }
        return dishes;
    }

    @Override
    public Dish get(long id) {
        if (db != null) {
            Cursor cursor = null;
            try {
                StringBuffer query = new StringBuffer(sqlGetAll).append(sqlWhereId);
                cursor = db.rawQuery(query.toString(), new String[]{String.valueOf(id)});
                if (cursor.moveToFirst()) {
                    return createDish(cursor);
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
    public long insert(Dish dish) {
        long id = exist(dish);

        if (dish == null) {
            return id;
        }

        try {
            if (db != null) {
                db.beginTransaction();
                ContentValues values = new ContentValues();
                values.put(Dish.COLUMN_TITLE, dish.getTitle());
                values.put(Dish.COLUMN_CREATED, dish.getCreated().getTime());
                values.put(Dish.COLUMN_KIND_ID, dish.getKind().getId());
                values.put(Dish.COLUMN_CELEBRATORY, dish.isCelebratory());
                values.put(Dish.COLUMN_EVERYDAY, dish.isEveryday());
                values.put(Dish.COLUMN_LENTEN, dish.isLenten());
                values.put(Dish.COLUMN_FISH, dish.containsFish());
                values.put(Dish.COLUMN_COOKING, dish.getCooking());
                if (id == -1) {
                    id = db.insert(Dish.TABLE_NAME, null, values);
                } else {
                    db.update(Dish.TABLE_NAME, values, DBContract._ID + " = ?", new String[]{String.valueOf(dish.getId())});
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
    public void update(Dish dish) {
        insert(dish);
    }

    public List<Dish> getFor(Kind kind) {
        List<Dish> dishes = new ArrayList<>();
        if (db != null) {
            Cursor cursor = null;
            try {
                StringBuffer query = new StringBuffer(sqlGetAll).append(sqlWhereKindId);
                cursor = db.rawQuery(query.toString(), new String[]{String.valueOf(kind.getId())});
                if (cursor.moveToFirst()) {
                    do {
                        Dish dish = createDish(cursor);
                        if (dish != null) {
                            dishes.add(dish);
                        }
                    } while (cursor.moveToNext());
                }
            } catch (SQLiteException e) {
                Log.e(LOG_TAG, e.getMessage());
            } finally {
                CursorHelper.closeCursor(cursor);
            }
        }
        return dishes;
    }

    public int getCountForKind(Kind kind) {
        if (kind == null) {
            return 0;
        }

        return getCount("select count(*) from " + Dish.TABLE_NAME + " where " + Dish.COLUMN_KIND_ID + " =" + kind.getId());
    }

    private Dish createDish(Cursor cursor) {
        if (cursor == null || cursor.isClosed()) {
            return null;
        }

        Dish.Builder builder = Dish.newBuilder();
        try {
            builder.setId(cursor.getLong(cursor.getColumnIndex("dish_id")))
                    .setTitle(cursor.getString(cursor.getColumnIndex("dish_title")))
                    .setCreated(cursor.getLong(cursor.getColumnIndex("dish_created")))
                    .setCooking(cursor.getString(cursor.getColumnIndex(Dish.COLUMN_COOKING)))
                    .setCelebratory(cursor.getString(cursor.getColumnIndex(Dish.COLUMN_CELEBRATORY)))
                    .setEveryday(cursor.getString(cursor.getColumnIndex(Dish.COLUMN_EVERYDAY)))
                    .setLenten(cursor.getString(cursor.getColumnIndex(Dish.COLUMN_LENTEN)))
                    .setFish(cursor.getString(cursor.getColumnIndex(Dish.COLUMN_FISH)))
                    .setLastCooking(cursor.getLong(cursor.getColumnIndex(Dish.COLUMN_LAST_COOKING)));

            Kind.Builder kindBuilder = Kind.newBuilder()
                    .setId(cursor.getLong(cursor.getColumnIndex("kind_id")))
                    .setCreated(cursor.getLong(cursor.getColumnIndex("kind_created")))
                    .setLabel(cursor.getString(cursor.getColumnIndex("kind_label")));

            builder.setKind(kindBuilder.build());
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
        }

        return builder.build();
    }


}
