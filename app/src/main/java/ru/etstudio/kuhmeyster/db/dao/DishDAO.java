package ru.etstudio.kuhmeyster.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
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
                        Dish dish = getDish(cursor);
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
                    return getDish(cursor);
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
                        Dish dish = getDish(cursor);
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

    public int getEverydayCount() {
        return getCount("select count(*) from " + Dish.TABLE_NAME + " where " + Dish.COLUMN_EVERYDAY + " = 1");
    }

    public int getCelebratoryCount() {
        return getCount("select count(*) from " + Dish.TABLE_NAME + " where " + Dish.COLUMN_CELEBRATORY + " = 1");
    }

    public int getEverydayLentenCount() {
        return getCount("select count(*) from " + Dish.TABLE_NAME + " where " + Dish.COLUMN_EVERYDAY + " = 1 and " + Dish.COLUMN_LENTEN + " = 1");
    }

    public int getCelebratoryLentenCount() {
        return getCount("select count(*) from " + Dish.TABLE_NAME + " where " + Dish.COLUMN_CELEBRATORY + " = 1 and " + Dish.COLUMN_LENTEN + " = 1");
    }

    private Dish getDish(Cursor cursor) {
        Dish dish = null;

        if (cursor == null || cursor.isClosed()) {
            return null;
        }

        try {
            dish = new Dish();
            dish.setId(cursor.getLong(cursor.getColumnIndex("dish_id")));
            dish.setTitle(cursor.getString(cursor.getColumnIndex("dish_title")));
            Date created = new Date(cursor.getLong(cursor.getColumnIndex("dish_created")));
            dish.setCreated(created);
            dish.setCooking(cursor.getString(cursor.getColumnIndex(Dish.COLUMN_COOKING)));
            boolean celebratory = Boolean.getBoolean(cursor.getString(cursor.getColumnIndex(Dish.COLUMN_CELEBRATORY)));
            dish.setCelebratory(celebratory);
            boolean everyday = Boolean.getBoolean(cursor.getString(cursor.getColumnIndex(Dish.COLUMN_EVERYDAY)));
            dish.setEveryday(everyday);
            boolean lenten = Boolean.getBoolean(cursor.getString(cursor.getColumnIndex(Dish.COLUMN_LENTEN)));
            dish.setLenten(lenten);
            boolean fish = Boolean.getBoolean(cursor.getString(cursor.getColumnIndex(Dish.COLUMN_FISH)));
            dish.setContainsFish(fish);

            Kind kind = new Kind();
            kind.setId(cursor.getLong(cursor.getColumnIndex("kind_id")));
            kind.setLabel(cursor.getString(cursor.getColumnIndex("kind_label")));
            Date kindCreated = new Date(cursor.getLong(cursor.getColumnIndex("kind_created")));
            kind.setCreated(kindCreated);
            dish.setKind(kind);
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
        }

        return dish;
    }


}
