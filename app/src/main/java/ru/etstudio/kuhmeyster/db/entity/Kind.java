package ru.etstudio.kuhmeyster.db.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class Kind implements DBContract, Parcelable {

    public static final String TABLE_NAME = "kind";

    public static final String COLUMN_TITLE = "title";

    public static final String COLUMN_CREATED = "created";

    public static final String SQL_CREATE_TABLE = new StringBuffer()
            .append("CREATE TABLE ")
            .append(TABLE_NAME)
            .append(" (")
            .append(_ID).append(" INTEGER PRIMARY KEY, ")
            .append(COLUMN_TITLE).append(" TEXT, ")
            .append(COLUMN_CREATED).append(" INTEGER")
            .append(")").toString();

    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;

    private long _id;

    private String kind;

    private Date created;

    public Kind() {

    }

    public Kind(long id, String kind, Date created) {
        this._id = id;
        this.kind = kind;
        this.created = created;
    }

    public Kind(String kind) {
        this.kind = kind;
        this.created = new Date();
    }

    protected Kind(Parcel in) {
        _id = in.readLong();
        kind = in.readString();
        created = new Date(in.readLong());
    }

    public static final Creator<Kind> CREATOR = new Creator<Kind>() {
        @Override
        public Kind createFromParcel(Parcel in) {
            return new Kind(in);
        }

        @Override
        public Kind[] newArray(int size) {
            return new Kind[size];
        }
    };

    @Override
    public long getId() {
        return _id;
    }

    public void setId(long id) {
        this._id = id;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getFormattedCreated() {
        if (created != null) {
            return new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(created);
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
        dest.writeString(kind);
        if (created != null) {
            dest.writeLong(created.getTime());
        }
    }
}
