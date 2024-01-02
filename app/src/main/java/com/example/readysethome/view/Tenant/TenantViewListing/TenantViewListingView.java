package com.example.readysethome.view.Tenant.TenantViewListing;

import java.util.Date;

public interface TenantViewListingView {
    String getCheckIn();

    String getCheckOut();

    void submit(Date checkInTime, Date checkOutTime);

}
