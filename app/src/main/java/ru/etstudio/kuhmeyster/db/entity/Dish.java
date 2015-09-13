package ru.etstudio.kuhmeyster.db.entity;

import java.util.Date;

public final class Dish implements DBContract {

    public static final String TABLE_NAME = "dish";

    public static final String COLUMN_KIND_ID = "kind_id";

    public static final String COLUMN_CREATED = "created";

    public static final String COLUMN_TITLE = "title";

    public static final String COLUMN_COOKING = "cooking";

    public static final String COLUMN_EVERYDAY = "everyday";

    public static final String COLUMN_LENTEN = "lenten";

    public static final String COLUMN_CELEBRATORY = "celebratory";

    public static final String COLUMN_FISH = "fish";

    public static final String SQL_CREATE_TABLE = new StringBuffer()
            .append("CREATE TABLE ")
            .append(TABLE_NAME)
            .append(" (")
            .append(_ID).append(" INTEGER PRIMARY KEY, ")
            .append(COLUMN_KIND_ID).append(" INTEGER, ")
            .append(COLUMN_CREATED).append(" DATETIME DEFAULT CURRENT_TIMESTAMP, ")
            .append(COLUMN_TITLE).append(" TEXT, ")
            .append(COLUMN_COOKING).append(" TEXT, ")
            .append(COLUMN_EVERYDAY).append(" INTEGER, ")
            .append(COLUMN_LENTEN).append(" INTEGER, ")
            .append(COLUMN_CELEBRATORY).append(" INTEGER, ")
            .append(COLUMN_FISH).append(" INTEGER")
            .append(")").toString();

    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;

    private long _id;

    private Kind kind;

    private Date created;

    private String title;

    private String cooking;

    private boolean everyday;

    private boolean lenten;

    private boolean celebratory;

    private boolean containsFish;

    public Dish() {
    }

    @Override
    public long getId() {
        return _id;
    }

    public void setId(long id) {
        this._id = id;
    }

    public Kind getKind() {
        return kind;
    }

    public void setKind(Kind kind) {
        this.kind = kind;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCooking() {
        return cooking;
    }

    public void setCooking(String cooking) {
        this.cooking = cooking;
    }

    public boolean isEveryday() {
        return everyday;
    }

    public void setEveryday(boolean everyday) {
        this.everyday = everyday;
    }

    public boolean isLenten() {
        return lenten;
    }

    public void setLenten(boolean lenten) {
        this.lenten = lenten;
    }

    public boolean isCelebratory() {
        return celebratory;
    }

    public void setCelebratory(boolean celebratory) {
        this.celebratory = celebratory;
    }

    public boolean containsFish() {
        return containsFish;
    }

    public void setContainsFish(boolean containsFish) {
        this.containsFish = containsFish;
    }
}
