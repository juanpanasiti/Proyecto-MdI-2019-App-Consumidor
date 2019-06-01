package com.example.changosconsumidor.model;

public class Product {
    private int id;
    private String mark;
    private String name;
    private float contentQuantity;
    private String contentUnit;
    private Category category;

    public int getID() {
        return id;
    }

    public String getMark() {
        return mark;
    }

    public String getName() {
        return name;
    }

    public float getContentQuantity() {
        return contentQuantity;
    }

    public String getContentUnit() {
        return contentUnit;
    }

    public Category getCategory() {
        return category;
    }
}
