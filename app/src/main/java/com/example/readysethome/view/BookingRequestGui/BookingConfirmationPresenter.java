package com.example.readysethome.view.BookingRequestGui;

import com.example.readysethome.dao.ListingDAO;
import com.example.readysethome.dao.TenantDAO;
import com.example.readysethome.model.BookingRequest;
import com.example.readysethome.model.Listing;
import com.example.readysethome.model.Tenant;
import java.util.Date;

public class BookingConfirmationPresenter {

    private BookingConfirmationView view;

    private BookingRequest bookingrequest;

    private ListingDAO listings;

    private TenantDAO tenants;
    private Listing listing;

    private Tenant tenant;
    Date checkIn;
    Date checkOut;

    public BookingConfirmationPresenter(BookingConfirmationView view, ListingDAO listings, Date checkIn, Date checkOut, int listing_id, String tenant_id, TenantDAO tenants) {
        this.view = view;
        this.listings=listings;
        this.checkIn = checkIn;
        this.checkOut = checkOut;

        this.listing = listings.findByID(listing_id);
        this.tenant= tenants.findByID(tenant_id);
    }

    public void onViewCreated() {
        String confirmationMessage = "Booking Request confirmed!";
        view.displayConfirmationMessage(confirmationMessage);
        //submit the booking request
        bookingrequest = tenant.makeBookingRequest(listing,checkIn,checkOut);
    }

    public void onHomePageClicked() {
        view.navigateToHomePage(tenant.getId());
    }

    public BookingRequest getBookingrequest() {
        return bookingrequest;
    }
}
