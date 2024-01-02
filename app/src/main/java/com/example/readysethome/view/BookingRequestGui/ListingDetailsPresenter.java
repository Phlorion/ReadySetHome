package com.example.readysethome.view.BookingRequestGui;

import com.example.readysethome.dao.ListingDAO;
import com.example.readysethome.model.Listing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ListingDetailsPresenter {
    private Listing listing;
    private ListingDetailsView view;
    private ListingDAO listingDAO;

    public ListingDetailsPresenter(ListingDetailsView view, ListingDAO listingDAO) {
        this.view = view;
        this.listingDAO = listingDAO;
    }



    protected boolean isListingAvailable(int listingId, Date checkInTime, Date checkOutTime) {

        Listing listing = listingDAO.findByID(listingId);
        return listing.getCalendar().isAvailable(checkInTime, checkOutTime);
    }

    public Date parseDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.parse(dateString);
    }
}
