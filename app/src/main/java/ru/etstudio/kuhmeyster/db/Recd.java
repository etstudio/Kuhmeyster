package ru.etstudio.kuhmeyster.db;

import java.util.Date;

public final class Recd {

    private int _id;

    private int dishId;

    private Date lastCooking;

    public Recd() {

    }

    public Recd(int id, int dishId) {
        this._id = id;
        this.dishId = dishId;
        this.lastCooking = new Date();
    }

    public Recd(int dishId) {
        this.dishId = dishId;
        this.lastCooking = new Date();
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public Date getLastCooking() {
        return lastCooking;
    }

    public void setLastCooking(Date lastCooking) {
        this.lastCooking = lastCooking;
    }
}
