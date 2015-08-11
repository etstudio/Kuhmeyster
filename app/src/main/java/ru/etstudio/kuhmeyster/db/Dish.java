package ru.etstudio.kuhmeyster.db;

import java.util.Date;

public class Dish {

    private int _id;

    private int kindId;

    private Date added;

    private String title;

    private String cooking;

    private boolean everyday;

    private boolean lenten;

    private boolean celebratory;

    private boolean containsFish;

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public int getKindId() {
        return kindId;
    }

    public void setKindId(int kindId) {
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
}
