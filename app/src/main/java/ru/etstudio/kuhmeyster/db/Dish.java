package ru.etstudio.kuhmeyster.db;

import android.provider.BaseColumns;

import java.util.Date;

public final class Dish {

    private long _id;

    private long kindId;

    private Date added;

    private String title;

    private String cooking;

    private boolean everyday;

    private boolean lenten;

    private boolean celebratory;

    private boolean containsFish;

    public static final String SQL_CREATE_TABLE_DISH = new StringBuffer()
            .append("CREATE TABLE ")
            .append(Entry.TABLE_NAME)
            .append(" (")
            .append(Entry._ID).append(" INTEGER PRIMARY KEY, ")
            .append(Entry.COLUMN_KIND_ID).append(" INTEGER, ")
            .append(Entry.COLUMN_ADD_DATE).append(" INTEGER, ")
            .append(Entry.COLUMN_TITLE).append(" TEXT, ")
            .append(Entry.COLUMN_COOKING).append(" TEXT, ")
            .append(Entry.COLUMN_EVERYDAY).append(" INTEGER, ")
            .append(Entry.COLUMN_LENTEN).append(" INTEGER, ")
            .append(Entry.COLUMN_CELEBRATORY).append(" INTEGER, ")
            .append(Entry.COLUMN_FISH).append(" INTEGER")
            .append(")").toString();

    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + Entry.TABLE_NAME;

    public Dish() {
    }

    public long getId() {
        return _id;
    }

    public void setId(long id) {
        this._id = id;
    }

    public long getKindId() {
        return kindId;
    }

    public void setKindId(long kindId) {
        this.kindId = kindId;
    }

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
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

    public static abstract class Entry implements BaseColumns {

        public static final String TABLE_NAME = "dish";

        public static final String COLUMN_KIND_ID = "kind_id";

        public static final String COLUMN_ADD_DATE = "add_date";

        public static final String COLUMN_TITLE = "title";

        public static final String COLUMN_COOKING = "cooking";

        public static final String COLUMN_EVERYDAY = "everyday";

        public static final String COLUMN_LENTEN = "lenten";

        public static final String COLUMN_CELEBRATORY = "celebratory";

        public static final String COLUMN_FISH = "fish";
    }
}
