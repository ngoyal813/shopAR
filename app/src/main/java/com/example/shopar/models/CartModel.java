package com.example.shopar.models;

public class CartModel {

    public CartModel(){}
    public CartModel(String PName, String PPrice, String quantity, String PId, String PImage) {
        this.PName = PName;
        this.PPrice = PPrice;
        this.quantity = quantity;
        this.PId = PId;
        this.PImage = PImage;
    }

    public String getPName() {
        return PName;
    }

    public void setPName(String PName) {
        this.PName = PName;
    }

    public String getPPrice() {
        return PPrice;
    }

    public void setPPrice(String PPrice) {
        this.PPrice = PPrice;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPId() {
        return PId;
    }

    public void setPId(String PId) {
        this.PId = PId;
    }

    public String getPImage() {
        return PImage;
    }

    public void setPImage(String PImage) {
        this.PImage = PImage;
    }

    private String PName,PPrice,quantity,PId,PImage;
}
