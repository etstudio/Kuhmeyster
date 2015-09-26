package ru.etstudio.kuhmeyster.adapter;

import android.graphics.drawable.Drawable;

public class Card {

    private Drawable image;

    private DishType type;

    public Card(DishType type, Drawable image) {
        this.type = type;
        this.image = image;
    }

    public String getLabel() {
        return type.getLabel();
    }

    public Drawable getImage() {
        return image;
    }

    public DishType getType() {
        return type;
    }
}
