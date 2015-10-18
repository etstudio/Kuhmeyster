package ru.etstudio.kuhmeyster.adapter;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Pack implements Parcelable {

    private DishType dishType;

    private List<String> kindIds = new ArrayList<>();

    private boolean isLenten;


    public Pack() {

    }

    protected Pack(Parcel in) {
        dishType = DishType.valueOf(in.readString());
        kindIds = in.createStringArrayList();
        isLenten = Boolean.valueOf(in.readString());
    }

    public static final Creator<Pack> CREATOR = new Creator<Pack>() {
        @Override
        public Pack createFromParcel(Parcel in) {
            return new Pack(in);
        }

        @Override
        public Pack[] newArray(int size) {
            return new Pack[size];
        }
    };

    public DishType getDishType() {
        return dishType;
    }

    public void setDishType(DishType dishType) {
        this.dishType = dishType;
    }

    public List<String> getKindIds() {
        return kindIds;
    }

    public void addKindIds(long kindId) {
        kindIds.add(String.valueOf(kindId));
    }

    public boolean isLenten() {
        return isLenten;
    }

    public void setLenten(boolean isLenten) {
        this.isLenten = isLenten;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dishType.name());
        dest.writeStringList(kindIds);
        dest.writeString(Boolean.toString(isLenten()));
    }
}
