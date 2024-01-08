package com.example.readysethome.view.Owner.OwnerAddListing.AddListingServices;

public class ListingServicesModel {
    String type, price;

    public ListingServicesModel(String type, String price) {
        this.type = type;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public String getPrice() {
        return price;
    }
}
