package ru.etstudio.kuhmeyster.db.entity;

import java.util.Date;

public final class Recd implements DBContract {

    public static final String TABLE_NAME = "recd";

    public static final String COLUMN_DISH_ID = "dish_id";

    public static final String COLUMN_LAST_COOKING = "last_cooking";

    public static final String SQL_CREATE_TABLE = new StringBuffer()
            .append("CREATE TABLE ")
            .append(TABLE_NAME)
            .append(" (")
            .append(_ID).append(" INTEGER PRIMARY KEY, ")
            .append(COLUMN_DISH_ID).append(" INTEGER, ")
            .append(COLUMN_LAST_COOKING).append(" DATETIME DEFAULT CURRENT_TIMESTAMP")
            .append(")").toString();

    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;

    private long _id;

    private Dish dish;

    private Date lastCooking;

    public Recd() {

    }

    public Recd(long id, Dish dish) {
        this._id = id;
        this.dish = dish;
    }

    public Recd(Dish dish) {
        this.dish = dish;
    }

    @Override
    public long getId() {
        return _id;
    }

    public void setId(long id) {
        this._id = id;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Date getLastCooking() {
        return lastCooking;
    }
}
