package com.example.readysethome.view.Owner;

public class OwnerPendingModel {
    String tenants_Name;

    String listingsTitle;
    int image;
    int brId;

    public OwnerPendingModel(String tenants_Name, String listingsTitle, int image, int brId) {
        this.tenants_Name = tenants_Name;
        this.listingsTitle = listingsTitle;
        this.image = image;
        this.brId = brId;
    }

    public String getTenants_Name() {
        return tenants_Name;
    }

    public int getBrId() {
        return brId;
    }

    public String getListingsTitle() {
        return listingsTitle;
    }

    public int getImage() {
        return image;
    }
}
