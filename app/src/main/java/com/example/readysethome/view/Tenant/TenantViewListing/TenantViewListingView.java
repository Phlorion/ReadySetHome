package com.example.readysethome.view.Tenant.TenantViewListing;

import java.util.Date;

public interface TenantViewListingView {
    void setListingTitle(String title);
    void setListingDesc(String desc);
    void setListingPrice(String price);
    void setListingLocation(String location);
    void setListingSize(String size);

    void setListingFloor(String floor);

    String getCheckInTV();
    String getCheckOutTV();
    void setCheckInTV(String checkIn);
    void setCheckOutTV(String checkOut);


    void setFloorTV(String floor);

    void submit(Date checkInTime, Date checkOutTime, int listing_id, String tenant_id);
    void showErrorMessage(String error);

    void setListingWifi(boolean wifi);

    void setListingBalcony(boolean balcony);

    void setListingLivingRoom(boolean livingRoom);

    void setListingBathrooms(int size);

    void setListingKitchens(int size);

    void setListingBedrooms(int size);

}
