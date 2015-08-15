package ru.etstudio.kuhmeyster.db;


import android.provider.BaseColumns;

import java.util.Date;

public final class Ingredient {

    private int _id;

    private String title;

    private Date createdAt;

    public static final String SQL_CREATE_TABLE = new StringBuffer()
            .append("CREATE TABLE ")
            .append(Entry.TABLE_NAME)
            .append(" (")
            .append(Entry._ID).append(" INTEGER PRIMARY KEY, ")
            .append(Entry.COLUMN_TITLE).append(" TEXT, ")
            .append(Entry.COLUMN_CREATED_AT).append(" DATETIME DEFAULT CURRENT_TIMESTAMP")
            .append(")").toString();

    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + Entry.TABLE_NAME;

    public Ingredient() {

    }

    public Ingredient(int id, String title) {
        this._id = id;
        this.title = title;
    }

    public Ingredient(String title) {
        this.title = title;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public static abstract class Entry implements BaseColumns {

        public static final String TABLE_NAME = "ingredient";

        public static final String COLUMN_TITLE = "title";

        public static final String COLUMN_CREATED_AT = "created_at";
    }
}
