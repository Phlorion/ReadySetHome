package com.example.readysethome.view.Tenant;

public class TenantHomeListingModel {
    String title;
    String desc;
    String price;
    int id;
    int image;

    public TenantHomeListingModel(String title, String desc, String price, int id, int image) {
        this.title = title;
        this.desc = desc;
        this.price = price;
        this.id = id;
        this.image = image;
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

    public int getId() {
        return id;
    }

    public int getImage() {
        return image;
    }
}
