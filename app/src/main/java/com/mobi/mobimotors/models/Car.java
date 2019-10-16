package com.mobi.mobimotors.models;

public class Car {
    private String name;
    private double price;


    public Car(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
    public String getPriceAsString(){
        return Double.toString(getPrice());
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
