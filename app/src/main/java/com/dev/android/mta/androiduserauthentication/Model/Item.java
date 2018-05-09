package com.dev.android.mta.androiduserauthentication.Model;


import android.content.Intent;

public class Item {
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
}