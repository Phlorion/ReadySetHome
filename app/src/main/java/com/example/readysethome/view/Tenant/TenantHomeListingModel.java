package com.example.readysethome.view.Tenant;

import com.example.readysethome.model.Calendar;
import com.example.readysethome.model.Listing;

public class TenantHomeListingModel {
    String title;
    String desc;
    String price;
    String ap_address;
    int id;
    int image;
    Listing listing;

    public TenantHomeListingModel(String title, String desc, String price, String ap_address, int id, int image, Listing listing) {
        this.title = title;
        this.desc = desc;
        this.price = price;
        this.ap_address = ap_address;
        this.id = id;
        this.image = image;
        this.listing = listing;
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

    public String getAp_address() {
        return ap_address;
    }

    public Listing getListing() {
        return listing;
    }
}
