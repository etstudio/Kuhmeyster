package ru.etstudio.kuhmeyster.adapter;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import ru.etstudio.kuhmeyster.db.entity.Kind;

public class Card implements Parcelable {

    private Kind kind;

    private Drawable image;

    private int dishCount;

    private Card() {
    }

    protected Card(Parcel in) {
        kind = in.readParcelable(Kind.class.getClassLoader());
        dishCount = in.readInt();
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

    public Kind getKind() {
        return kind;
    }

    public Drawable getImage() {
        return image;
    }

    public int getDishCount() {
        return dishCount;
    }

    public static Builder newBulder() {
        return new Card().new Builder();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(kind, flags);
        dest.writeInt(dishCount);
    }

    public class Builder {

        private Builder() {

        }

        public Builder setKind(Kind kind) {
            Card.this.kind = kind;
            return this;
        }

        public Builder setImage(Drawable image) {
            Card.this.image = image;
            return this;
        }

        public Builder setDishCount(int value) {
            Card.this.dishCount = value;
            return this;
        }

        public Card build() {
            return Card.this;
        }
    }

}
