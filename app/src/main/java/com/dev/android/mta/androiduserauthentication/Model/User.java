package com.dev.android.mta.androiduserauthentication.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shahar on 04/05/2018.
 */

public class User implements Parcelable {

    private String email;
    private int totalPurchase;
    private List<String> myCars = new ArrayList<>();

    public User() {
    }

    public User(String email, int totalPurchase, List<String> mySongs) {
        this.email = email;
        this.totalPurchase = totalPurchase;
        this.myCars = mySongs;
    }

    public String getEmail() {
        return email;
    }
    public int getTotalPurchase() {
        return totalPurchase;
    }

    public void upgdateTotalPurchase(int newPurcahsePrice) {
        this.totalPurchase += newPurcahsePrice;
    }

    public List<String> getMyCars() {
        return myCars;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(email);
        //parcel.writeInt(totalPurchase);
        parcel.writeList(myCars);
    }

    public User(Parcel in) {
        this.email = in.readString();
        in.readList(myCars, String.class.getClassLoader());
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
