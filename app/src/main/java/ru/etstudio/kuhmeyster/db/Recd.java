package ru.etstudio.kuhmeyster.db;

import android.provider.BaseColumns;

import java.util.Date;

public final class Recd {

    public static final String SQL_CREATE_TABLE = new StringBuffer()
            .append("CREATE TABLE ")
            .append(Entry.TABLE_NAME)
            .append(" (")
            .append(Entry._ID).append(" INTEGER PRIMARY KEY, ")
            .append(Entry.COLUMN_DISH_ID).append(" INTEGER, ")
            .append(Entry.COLUMN_LAST_COOKING).append(" DATETIME DEFAULT CURRENT_TIMESTAMP")
            .append(")").toString();

    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + Entry.TABLE_NAME;

    private int _id;

    private int dishId;

    private Date lastCooking;

    public Recd() {

    }

    public Recd(int id, int dishId) {
        this._id = id;
        this.dishId = dishId;
    }

    public Recd(int dishId) {
        this.dishId = dishId;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public Date getLastCooking() {
        return lastCooking;
    }

    public static abstract class Entry implements BaseColumns {

        public static final String TABLE_NAME = "recd";

        public static final String COLUMN_DISH_ID = "dish_id";

        public static final String COLUMN_LAST_COOKING = "last_cooking";
    }
}
