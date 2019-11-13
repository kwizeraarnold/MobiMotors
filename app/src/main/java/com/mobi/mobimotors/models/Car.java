package com.mobi.mobimotors.models;

import java.io.Serializable;

@SuppressWarnings("serial") //With this annotation we are going to hide compiler warnings
public class Car implements Serializable {
    private String name;
    private String price;
    private String imageUrl;
    private String id;


//    public Car(String name, String price) {
//        this.name = name;
//        this.price = price;
//    }
    public Car(String name, String price,String imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }
    public Car(String name, String price,String imageUrl,String id) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
