package ru.etstudio.kuhmeyster.adapter;

import android.graphics.drawable.Drawable;

public class Card {

    private Drawable image;

    private DishType type;

    private String statisticText;

    private int everydayCount;

    private int everydayLentenCount;

    private int celebratoryCount;

    private int celebratoryLentenCount;

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

    public String getStatisticText() {
        return statisticText;
    }

    public void setStatisticText(String statisticText) {
        this.statisticText = statisticText;
    }

    public void setEverydayCount(int everydayCount) {
        this.everydayCount = everydayCount;
    }

    public void setEverydayLentenCount(int everydayLentenCount) {
        this.everydayLentenCount = everydayLentenCount;
    }

    public void setCelebratoryCount(int celebratoryCount) {
        this.celebratoryCount = celebratoryCount;
    }

    public void setCelebratoryLentenCount(int celebratoryLentenCount) {
        this.celebratoryLentenCount = celebratoryLentenCount;
    }

    public int getCount() {
        if (type == DishType.EVERYDAY) {
            return everydayCount;
        } else if (type == DishType.CELEBRATORY) {
            return celebratoryCount;
        }
        return 0;
    }

    public int getLentenCount() {
        if (type == DishType.EVERYDAY) {
            return everydayLentenCount;
        } else if (type == DishType.CELEBRATORY) {
            return celebratoryLentenCount;
        }
        return 0;
    }
}
