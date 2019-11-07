package com.mobi.mobimotors.models;

public class Question {
    String name, Description;

    public Question(String name, String description) {
        this.name = name;
        Description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Override
    public String toString() {
        return "Question{" +
            "name='" + name + '\'' +
            ", Description='" + Description + '\'' +
            '}';
    }
}
