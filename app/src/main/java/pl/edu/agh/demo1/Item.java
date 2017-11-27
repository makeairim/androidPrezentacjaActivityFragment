package pl.edu.agh.demo1;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by bwolcerz on 27.11.2017.
 */

public class Item implements Parcelable{
    private String description;
    public Item() {
    }

    public Item(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.description);
    }

    protected Item(Parcel in) {
        this.description = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}
