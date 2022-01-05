package com.example.shopar.models;

public class categoryModel {

    public categoryModel() {

    }

    public int getCategory_image() {
        return category_image;
    }

    public void setCategory_image(int category_image) {
        this.category_image = category_image;
    }

    public String getCategory_title() {
        return category_title;
    }

    public void setCategory_title(String category_title) {
        this.category_title = category_title;
    }

    public categoryModel(int category_image, String category_title) {
        this.category_image = category_image;
        this.category_title = category_title;
    }

    private int category_image;
    private String category_title;
}
