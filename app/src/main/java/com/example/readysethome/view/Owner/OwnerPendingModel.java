package com.example.readysethome.view.Owner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OwnerPendingModel {
    String tenants_Name;

    String listingsTitle;
    int image;
    int brId;
    Date checkIn;
    Date checkOut;

    public OwnerPendingModel(String tenants_Name, String listingsTitle, int image, int brId, Date checkIn, Date checkOut) {
        this.tenants_Name = tenants_Name;
        this.listingsTitle = listingsTitle;
        this.image = image;
        this.brId = brId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
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

    public String getCheckIn() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(checkIn).toString();
    }

    public String getCheckOut() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(checkOut).toString();
    }
}
