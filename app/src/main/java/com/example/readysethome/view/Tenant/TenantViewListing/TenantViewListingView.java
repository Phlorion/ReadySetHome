package com.example.readysethome.view.Tenant.TenantViewListing;

import java.util.Date;

public interface TenantViewListingView {
    void setListingTitle(String title);
    void setListingDesc(String desc);
    void setListingPrice(String price);
    void setListingLocation(String location);
    void setListingSize(String size);
    String getCheckInTV();
    String getCheckOutTV();
    void setCheckInTV(String checkIn);
    void setCheckOutTV(String checkOut);
    void submit(Date checkInTime, Date checkOutTime, int listing_id, String tenant_id);
    void showErrorMessage(String error);

}
