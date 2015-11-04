package ru.etstudio.kuhmeyster.db.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class Dish implements DBContract, Parcelable {

    public static final String TABLE_NAME = "dish";

    public static final String COLUMN_KIND_ID = "kind_id";

    public static final String COLUMN_CREATED = "created";

    public static final String COLUMN_TITLE = "title";

    public static final String COLUMN_COOKING = "cooking";

    public static final String COLUMN_EVERYDAY = "everyday";

    public static final String COLUMN_LENTEN = "lenten";

    public static final String COLUMN_CELEBRATORY = "celebratory";

    public static final String COLUMN_FISH = "fish";

    public static final String COLUMN_LAST_COOKING = "last_cooking";

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
            .append(COLUMN_FISH).append(" INTEGER, ")
            .append(COLUMN_LAST_COOKING).append(" DATETIME ")
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

    private Date lastCooking;

    private Dish() {
    }

    protected Dish(Parcel in) {
        _id = in.readLong();
        kind = in.readParcelable(Kind.class.getClassLoader());
        created = new Date(in.readLong());
        title = in.readString();
        cooking = in.readString();
        everyday = in.readByte() != 0;
        lenten = in.readByte() != 0;
        celebratory = in.readByte() != 0;
        containsFish = in.readByte() != 0;
        long lastCookingTimestamp = in.readLong();
        if (lastCookingTimestamp > 0) {
            lastCooking = new Date(lastCookingTimestamp);
        }
    }

    public static Builder newBuilder() {
        return new Dish().new Builder();
    }

    public static final Creator<Dish> CREATOR = new Creator<Dish>() {
        @Override
        public Dish createFromParcel(Parcel in) {
            return new Dish(in);
        }

        @Override
        public Dish[] newArray(int size) {
            return new Dish[size];
        }
    };

    @Override
    public long getId() {
        return _id;
    }

    public Kind getKind() {
        return kind;
    }

    public Date getCreated() {
        return created;
    }

    public String getTitle() {
        return title;
    }

    public String getCooking() {
        return cooking;
    }

    public boolean isEveryday() {
        return everyday;
    }

    public boolean isLenten() {
        return lenten;
    }

    public boolean isCelebratory() {
        return celebratory;
    }

    public boolean containsFish() {
        return containsFish;
    }

    public Date getLastCooking() {
        return lastCooking;
    }

    public String getFormattedLastCooking() {
        if (lastCooking != null) {
            return new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(lastCooking);
        }
        return "";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(_id);
        dest.writeParcelable(kind, flags);
        dest.writeLong(created.getTime());
        dest.writeString(title);
        dest.writeString(cooking);
        dest.writeByte((byte) (everyday ? 1 : 0));
        dest.writeByte((byte) (lenten ? 1 : 0));
        dest.writeByte((byte) (celebratory ? 1 : 0));
        dest.writeByte((byte) (containsFish ? 1 : 0));
        if (lastCooking != null) {
            dest.writeLong(lastCooking.getTime());
        }
    }

    public class Builder {

        private Builder() {

        }

        public Builder setId(long id) {
            Dish.this._id = id;
            return this;
        }

        public Builder setKind(Kind kind) {
            Dish.this.kind = kind;
            return this;
        }

        public Builder setCreated(long time) {
            Dish.this.created = new Date(time);
            return this;
        }

        public Builder setTitle(String title) {
            Dish.this.title = title;
            return this;
        }

        public Builder setCooking(String cooking) {
            Dish.this.cooking = cooking;
            return this;
        }

        public Builder setEveryday(String everyday) {
            Dish.this.everyday = Boolean.getBoolean(everyday);
            return this;
        }

        public Builder setLenten(String lenten) {
            Dish.this.lenten = Boolean.getBoolean(lenten);
            return this;
        }

        public Builder setCelebratory(String celebratory) {
            Dish.this.celebratory = Boolean.getBoolean(celebratory);
            return this;
        }

        public Builder setFish(String containsFish) {
            Dish.this.containsFish = Boolean.getBoolean(containsFish);
            return this;
        }

        public Builder setLastCooking(long time) {
            Dish.this.lastCooking = null;
            if (time > 0) {
                Dish.this.lastCooking = new Date(time);
            }
            return this;
        }

        public Dish build() {
            return Dish.this;
        }
    }
}
