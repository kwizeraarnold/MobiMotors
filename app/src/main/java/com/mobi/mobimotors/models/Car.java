package com.mobi.mobimotors.models;

public class Car {
    private String name;
    private String price;
    private String imageUrl;


    public Car(String name, String price) {
        this.name = name;
        this.price = price;
    }
    public Car(String name, String price,String imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
