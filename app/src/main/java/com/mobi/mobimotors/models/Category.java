package com.mobi.mobimotors.models;

public class Category {
    private String name;
    private String label;
    private int imageId;

    public Category(String name, String label) {
        this.name = name;
        this.label = label;
    }
    public Category(String name, String label,int imageId) {
        this.name = name;
        this.label = label;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
