package com.example.readysethome.view.Owner;

public class OwnerHomeListingModel {
    String title;
    String desc;
    String price;
    int image;

    public OwnerHomeListingModel(String title, String desc, String price, int image) {
        this.title = title;
        this.desc = desc;
        this.price = price;
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

    public int getImage() {
        return image;
    }
}
