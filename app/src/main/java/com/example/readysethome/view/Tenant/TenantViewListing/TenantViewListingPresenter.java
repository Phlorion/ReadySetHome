package com.example.readysethome.view.Tenant.TenantViewListing;

import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.example.readysethome.dao.ListingDAO;
import com.example.readysethome.model.Calendar;
import com.example.readysethome.model.Listing;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;

public class TenantViewListingPresenter {
    private Listing listing;
    private TenantViewListingView view;
    private ListingDAO listingDAO;
    private Date checkIn;
    private Date checkOut;

    public TenantViewListingPresenter(TenantViewListingView view, ListingDAO listingDAO, int listing_id) {
        this.view = view;
        this.listingDAO = listingDAO;

        this.listing = listingDAO.findByID(listing_id);
        setUpListingViewPage();

        // test
        /*
        java.util.Calendar c = java.util.Calendar.getInstance();
        Date d1 = new Date();
        c.set(java.util.Calendar.YEAR, 2024);
        c.set(java.util.Calendar.MONTH, 0);
        c.set(java.util.Calendar.DAY_OF_MONTH, 13);
        d1.setTime(c.getTimeInMillis());
        Date d2 = new Date();
        c.set(java.util.Calendar.YEAR, 2024);
        c.set(java.util.Calendar.MONTH, 0);
        c.set(java.util.Calendar.DAY_OF_MONTH, 23);
        d2.setTime(c.getTimeInMillis());
        this.listing.getCalendar().setUnavailable(d1, d2);
         */
    }

    protected void setUpListingViewPage() {
        view.setListingTitle(listing.getTitle());
        view.setListingDesc(listing.getDescription());
        view.setListingPrice(Double.toString(listing.getPrice()) + "€");
        view.setListingLocation(listing.getApartment().getLocation().toString());
        view.setListingSize(Double.toString(listing.getApartment().getSize()) + " m²");
    }

    protected boolean isListingAvailable(Date checkInTime, Date checkOutTime) {
        return listing.getCalendar().isAvailable(checkInTime, checkOutTime);
    }

    protected Date parseDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        return dateFormat.parse(dateString);
    }

    public void setCheckIn(String checkIn) throws ParseException {
        view.setCheckInTV(checkIn);
        this.checkIn = parseDate(checkIn);
    }

    public void setCheckOut(String checkOut) throws ParseException {
        view.setCheckOutTV(checkOut);
        this.checkOut = parseDate(checkOut);
    }

    /**
     * Create the DatePickerDialog.
     * @return The DatePickerDialog
     */
    public com.wdullaer.materialdatetimepicker.date.DatePickerDialog createDPD() {
        java.util.Calendar c = java.util.Calendar.getInstance();
        return DatePickerDialog.newInstance(
                (DatePickerDialog.OnDateSetListener)view,
                c.get(java.util.Calendar.YEAR),
                c.get(java.util.Calendar.MONTH),
                c.get(java.util.Calendar.DAY_OF_MONTH)
        );
    }

    /**
     * Set all the available dates in the DatePickerDialog.
     * @param dpd The DatePickerDialog
     * @param checkIn Check if the user pressed check in (so we can set unavailable all the dates before the check in)
     */
    public void setAvailableInCalendar(DatePickerDialog dpd, boolean checkIn) {
        // disable past dates
        dpd.setMinDate(java.util.Calendar.getInstance());

        // if in check out disable all past date of check in
        java.util.Calendar c = java.util.Calendar.getInstance();
        if (this.checkIn != null && !checkIn) {
            c.setTime(this.checkIn);
            dpd.setMinDate(c);
        } else if (this.checkOut != null && checkIn) {
            c.setTime(this.checkOut);
            dpd.setMaxDate(c);
        }

        // disable unavailable dates
        Calendar l_calendar = listing.getCalendar();
        ArrayList<java.util.Calendar> unavailable = new ArrayList<>();

        // for every kay in l_calendar
        Enumeration<Date> e = l_calendar.getAvailability().keys();
        while (e.hasMoreElements()) {
            Date key = e.nextElement(); // check in
            Date value = l_calendar.getAvailability().get(key); // check put

            // convert check in, check out dates to local dates
            LocalDate startDate = Instant.ofEpochMilli(key.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endDate = Instant.ofEpochMilli(value.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();

            // for every day from the check in until the check out
            while (startDate.isBefore(endDate) || startDate.equals(endDate)) {
                // add a calendar instance of the local date
                java.util.Calendar temp = java.util.Calendar.getInstance();
                temp.setTime(Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant())); // convert local date to date and set calendar time
                unavailable.add(temp);
                startDate = startDate.plusDays(1); // get the next day
            }
        }

        // disable unavailable days in the dpd
        java.util.Calendar[] _unavailable = new java.util.Calendar[unavailable.size()];
        for (int i=0; i<unavailable.size(); i++) {
            _unavailable[i] = unavailable.get(i);
        }
        dpd.setDisabledDays(_unavailable);
    }

    public void handleSubmission() {
        if (this.checkIn == null || this.checkOut == null) {
            view.showErrorMessage("Please fill the necessary info");
            return;
        }

        // Check listing availability
        if (isListingAvailable(this.checkIn, this.checkOut)) {
            // Navigate to BookingRequestActivity when the Submit Request button is clicked
            view.submit(this.checkIn, this.checkOut, listing.getListing_id());
        } else {
            // Show availability error
            view.showErrorMessage("Unavailable");
        }
    }
}
