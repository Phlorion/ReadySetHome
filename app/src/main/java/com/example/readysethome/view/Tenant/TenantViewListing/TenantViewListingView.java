package com.example.readysethome.view.Tenant.TenantViewListing;

import java.util.Date;

public interface TenantViewListingView {
    String getCheckInTV();
    String getCheckOutTV();
    void setCheckInTV(String checkIn);
    void setCheckOutTV(String checkOut);
    void submit(Date checkInTime, Date checkOutTime);
    void showErrorMessage(String error);

}
