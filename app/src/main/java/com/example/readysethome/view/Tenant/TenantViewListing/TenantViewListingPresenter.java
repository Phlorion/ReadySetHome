package com.example.readysethome.view.Tenant.TenantViewListing;

import android.content.Intent;

import com.example.readysethome.dao.ListingDAO;
import com.example.readysethome.model.Listing;
import com.example.readysethome.view.BookingRequestGui.BookingRequestActivity;
import com.example.readysethome.view.BookingRequestGui.ListingDetailsView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TenantViewListingPresenter {
    private Listing listing;
    private TenantViewListingView view;
    private ListingDAO listingDAO;

    public TenantViewListingPresenter(TenantViewListingView view, ListingDAO listingDAO, int listing_id) {
        this.view = view;
        this.listingDAO = listingDAO;

        this.listing = listingDAO.findByID(listing_id);
    }

    protected boolean isListingAvailable(int listingId, Date checkInTime, Date checkOutTime) {
        Listing listing = listingDAO.findByID(listingId);
        return listing.getCalendar().isAvailable(checkInTime, checkOutTime);
    }

    public Date parseDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.parse(dateString);
    }

    public void handleSubmission() {
        try{
            // Get the entered check-in and check-out times
            Date checkInTime = parseDate(view.getCheckIn());
            Date checkOutTime = parseDate(view.getCheckOut());

            // Check listing availability
            if (isListingAvailable(listing.getListing_id(), checkInTime, checkOutTime)) {
                // Navigate to BookingRequestActivity when the Submit Request button is clicked
                view.submit(checkInTime, checkOutTime);
            } else {
                // Show availability error
                //TODO: logika san to login tha einai
            }
        }catch(ParseException e){
            e.printStackTrace();
        }
    }
}
