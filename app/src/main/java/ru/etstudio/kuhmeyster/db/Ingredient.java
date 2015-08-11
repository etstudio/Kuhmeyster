package ru.etstudio.kuhmeyster.db;


import java.util.Date;

public class Ingredient {

    private int _id;

    private String title;

    private Date added;

    public Ingredient(int id, String title) {
        this._id = id;
        this.title = title;
        this.added = new Date();
    }

    public Ingredient(String title) {
        this.title = title;
        this.added = new Date();
    }
}
