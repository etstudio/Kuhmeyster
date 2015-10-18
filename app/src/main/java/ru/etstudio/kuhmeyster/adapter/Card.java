package ru.etstudio.kuhmeyster.adapter;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import ru.etstudio.kuhmeyster.db.entity.Dish;

public class Card implements Parcelable {

    private Drawable image;

    private DishType dishType;

    private String statisticText;

    private int everydayCount;

    private int everydayLentenCount;

    private int celebratoryCount;

    private int celebratoryLentenCount;

    public Card(DishType dishType, Drawable image) {
        this.dishType = dishType;
        this.image = image;
    }

    protected Card(Parcel in) {
        dishType = DishType.valueOf(in.readString());
        statisticText = in.readString();
        everydayCount = in.readInt();
        everydayLentenCount = in.readInt();
        celebratoryCount = in.readInt();
        celebratoryLentenCount = in.readInt();
    }


    public static final Creator<Card> CREATOR = new Creator<Card>() {
        @Override
        public Card createFromParcel(Parcel in) {
            return new Card(in);
        }

        @Override
        public Card[] newArray(int size) {
            return new Card[size];
        }
    };

    public String getLabel() {
        return dishType.getLabel();
    }

    public Drawable getImage() {
        return image;
    }

    public DishType getDishType() {
        return dishType;
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
        if (dishType == DishType.EVERYDAY) {
            return everydayCount;
        } else if (dishType == DishType.CELEBRATORY) {
            return celebratoryCount;
        }
        return 0;
    }

    public int getLentenCount() {
        if (dishType == DishType.EVERYDAY) {
            return everydayLentenCount;
        } else if (dishType == DishType.CELEBRATORY) {
            return celebratoryLentenCount;
        }
        return 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dishType.name());
        dest.writeString(statisticText);
        dest.writeInt(everydayCount);
        dest.writeInt(everydayLentenCount);
        dest.writeInt(celebratoryCount);
        dest.writeInt(celebratoryLentenCount);
    }
}
