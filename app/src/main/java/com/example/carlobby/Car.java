package com.example.carlobby;

import android.graphics.Bitmap;

public class Car {

    private int id;
    private String make;
    private String model;
    private String condition;
    private String cylinders;
    private int year;
    private int doors;
    private double price;
    private String color;
    private Bitmap thumbnail;
    private Bitmap fullImage;
    private boolean isSold;

    public Car() {}

    public Car(int id, String make, String model, String condition, String cylinders, int year,
               int doors, double price, String color, boolean isSold) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.condition = condition;
        this.cylinders = cylinders;
        this.year = year;
        this.doors = doors;
        this.price = price;
        this.color = color;
        this.thumbnail = thumbnail;
        this.fullImage = fullImage;
        this.isSold = isSold;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCylinders() {
        return cylinders;
    }

    public void setCylinders(String cylinders) {
        this.cylinders = cylinders;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    public boolean getIsSold() {
        return isSold;
    }

    public void setIsSold(boolean isSold) {
        this.isSold = isSold;
    }


}



