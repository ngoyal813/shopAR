package com.example.shopar.models;

public class productavailablemodel {

    public  productavailablemodel(){

    }

    public productavailablemodel(String category, String date, String description, String image, String model, String name, String pid, String price, String time) {
        this.category = category;
        this.date = date;
        this.description = description;
        this.image = image;
        this.model = model;
        this.name = name;
        this.pid = pid;
        this.price = price;
        this.time = time;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private  String category,date,description,image,model,name,pid,price,time;
}
