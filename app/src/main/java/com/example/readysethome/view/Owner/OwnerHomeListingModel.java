package com.example.readysethome.view.Owner;

public class OwnerHomeListingModel {
    String title;
    String desc;
    String price;
    int image;
    int id;

    public OwnerHomeListingModel(String title, String desc, String price, int image, int id) {
        this.title = title;
        this.desc = desc;
        this.price = price;
        this.image = image;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getPrice() {
        return price;
    }

    public int getImage() {
        return image;
    }

    public int getListing() {
        return id;
    }
}
