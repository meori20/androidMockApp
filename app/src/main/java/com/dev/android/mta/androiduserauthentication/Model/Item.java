package com.dev.android.mta.androiduserauthentication.Model;


import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {
    private String carColor;
    private String carImage;
    private String carMake;
    private String carModel;
    private int carModelYear;
    private String price;


    public Item(String carColor, String carImage, String carMake, String carModel, int carModelYear, String price) {
        this.carColor = carColor;
        this.carImage = carImage;
        this.carMake = carMake;
        this.carModel = carModel;
        this.carModelYear = carModelYear;
        this.price = price;
    }
    public Item(){}

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getCarImage() {
        return carImage;
    }

    public void setCarImage(String carImage) {
        this.carImage = carImage;
    }

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarModelYear() {
        return Integer.toString(this.carModelYear);
    }

    public void setCarModelYear(int carModelYear) {
        this.carModelYear = carModelYear;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(carColor);
        parcel.writeString(carImage);
        parcel.writeString(carMake);
        parcel.writeString(carModel);
        parcel.writeString(price);
        parcel.writeInt(carModelYear);
    }
    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }
        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
        private Item(Parcel in){
            this.carColor = in.readString();;
            this.carImage = in.readString();;
            this.carMake = in.readString();;
            this.carModel = in.readString();;
            this.carModelYear = in.readInt();
            this.price = in.readString();
        }
}