package ru.etstudio.kuhmeyster.db;


import java.util.Date;

public final class Ingredient {

    private int _id;

    private String title;

    private Date added;

    public Ingredient() {

    }

    public Ingredient(int id, String title) {
        this._id = id;
        this.title = title;
        this.added = new Date();
    }

    public Ingredient(String title) {
        this.title = title;
        this.added = new Date();
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

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
    }
}
