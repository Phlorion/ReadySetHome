package com.example.readysethome.view.Tenant.TenantViewListing;

import java.util.Date;

public interface TenantViewListingView {
    void setListingTitle(String title);
    void setListingDesc(String desc);
    void setListingPrice(String price);
    void setListingLocation(String location);
    void setListingSize(String size);
    void setOwnerName(String ownerName);
    void setBathrooms(String bathrooms);
    void setWifi(Boolean wifi);
    void setBalcony(Boolean balcony);
    void setBedrooms(String bedrooms);
    void setKitchens(String kitchens);
    void setFloor(String floor);
    String getCheckInTV();
    String getCheckOutTV();
    void setCheckInTV(String checkIn);
    void setCheckOutTV(String checkOut);
    void submit(Date checkInTime, Date checkOutTime, int listing_id, String tenant_id);
    void showErrorMessage(String error);

}
