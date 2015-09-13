package ru.etstudio.kuhmeyster.db.entity;


import java.util.Date;

public final class Ingredient implements DBContract {

    public static final String TABLE_NAME = "ingredient";

    public static final String COLUMN_TITLE = "title";

    public static final String COLUMN_CREATED = "created";

    public static final String SQL_CREATE_TABLE = new StringBuffer()
            .append("CREATE TABLE ")
            .append(TABLE_NAME)
            .append(" (")
            .append(_ID).append(" INTEGER PRIMARY KEY, ")
            .append(COLUMN_TITLE).append(" TEXT, ")
            .append(COLUMN_CREATED).append(" DATETIME DEFAULT CURRENT_TIMESTAMP")
            .append(")").toString();

    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;

    private long _id;

    private String title;

    private Date created;

    public Ingredient() {

    }

    public Ingredient(long id, String title) {
        this._id = id;
        this.title = title;
    }

    public Ingredient(String title) {
        this.title = title;
    }

    @Override
    public long getId() {
        return _id;
    }

    public void setId(long id) {
        this._id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreated() {
        return created;
    }
}
