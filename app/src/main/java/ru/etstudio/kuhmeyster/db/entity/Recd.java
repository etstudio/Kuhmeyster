package ru.etstudio.kuhmeyster.db.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

@Deprecated
public final class Recd implements DBContract, Parcelable {

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

    private Recd() {

    }

    protected Recd(Parcel in) {
        _id = in.readLong();
        dish = in.readParcelable(Dish.class.getClassLoader());
        lastCooking = new Date(in.readLong());
    }

    public static final Creator<Recd> CREATOR = new Creator<Recd>() {
        @Override
        public Recd createFromParcel(Parcel in) {
            return new Recd(in);
        }

        @Override
        public Recd[] newArray(int size) {
            return new Recd[size];
        }
    };

    @Override
    public long getId() {
        return _id;
    }

    public Dish getDish() {
        return dish;
    }

    public Date getLastCooking() {
        return lastCooking;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(_id);
        dest.writeParcelable(dish, flags);
        dest.writeLong(lastCooking.getTime());
    }
}
