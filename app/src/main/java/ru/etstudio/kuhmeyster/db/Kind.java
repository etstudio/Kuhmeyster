package ru.etstudio.kuhmeyster.db;

import java.util.Date;

public class Kind {

    private int _id;

    private String kind;

    private Date added;

    public Kind(int id, String kind, Date added) {
        this._id = id;
        this.kind = kind;
        this.added = added;
    }

    public Kind(String kind, Date added) {
        this.kind = kind;
        this.added = added;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
    }
}
