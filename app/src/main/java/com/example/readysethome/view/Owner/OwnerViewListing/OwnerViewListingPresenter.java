package com.example.readysethome.view.Owner.OwnerViewListing;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.readysethome.R;
import com.example.readysethome.dao.ListingDAO;
import com.example.readysethome.dao.TenantDAO;
import com.example.readysethome.model.Booking;
import com.example.readysethome.model.Listing;
import com.example.readysethome.model.Owner;
import com.example.readysethome.model.Tenant;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class OwnerViewListingPresenter {
    private OwnerViewListingView view;
    private ListingDAO listings;
    private TenantDAO tenants;
    private Listing listing;
    private List<Tenant> tenantList;



    public OwnerViewListingPresenter(OwnerViewListingView view, ListingDAO listings, TenantDAO tenants, int id) {
        this.view = view;
        this.listings = listings;
        this.tenants = tenants;
        listing = listings.findByID(id);
        tenantList = tenants.findAll();
    }

    public Listing getListing() {
        return listing;
    }

    public int findTheBookings(int listingId, String yearmonth) {
        int counter = 0;
        for (Tenant t : tenantList) {
            ArrayList<Booking> tmpBookings = t.getBookings();
            for (Booking b : tmpBookings) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
                String bookingYearMonth = dateFormat.format(b.getCheckIn());
                if (b.getListing().getListing_id() == listingId && bookingYearMonth.equals(yearmonth)) {
                    counter++;
                }
            }
        }
        return counter;
    }

    public void setUpBasicInfo() {
        view.setTitle(listing.getTitle());
        view.setDesc(listing.getDescription());
        view.setPrice(Double.toString(listing.getPrice()));
        if (listing.getPhotos() != null)
            view.setImage(listing.getPhotos()[0]);
        else
            view.setImage(R.drawable.child_po);
    }

    public void submitPressed() {

        String year = view.getYear();
        String month = String.valueOf(view.getMonth());
        if (month.length() == 1) {
            month = "0" + month;
        }

        String yearmonth = year + "-" + month;

        if (listing.getMonthlyIncome().get(yearmonth) != null) {
            double monthlyIncome = listing.getMonthlyIncome().get(yearmonth);
            view.setIncomePerMonth(Double.toString(monthlyIncome));
        }
        else {
            view.setIncomePerMonth(Double.toString(0.));
        }

        if (listing.getMonthlyCancellations().get(yearmonth) != null) {
            int monthlyCancellations = listing.getMonthlyCancellations().get(yearmonth);
            view.setCancellationsPerMonth(Integer.toString(monthlyCancellations));
        }
        else {
            view.setCancellationsPerMonth(Integer.toString(0));
        }

        int bookingsPerMonth = findTheBookings(listing.getListing_id(), yearmonth);
        view.setBookingsPerMonth(Integer.toString(bookingsPerMonth));


        Calendar c1 = Calendar.getInstance();
        c1.set(Calendar.YEAR, Integer.parseInt(year));
        c1.set(Calendar.MONTH, Integer.parseInt(month));
        c1.set(Calendar.DAY_OF_MONTH, 0);
        c1.set(Calendar.HOUR_OF_DAY, 0);
        c1.set(Calendar.MINUTE, 0);
        c1.set(Calendar.SECOND, 0);

        double occupancy = listing.calculateOccupancy(c1);
        view.setOccupancy(Double.toString(occupancy));
    }

    /**
     * Delete this registered listing
     */
    public Listing deleteListing() {
        Owner owner = listing.getOwner();
        ArrayList<Booking> bookings = new ArrayList<Booking>();
        for (Tenant t : tenants.findAll()) {
            for (Booking b : t.getBookings()) {
                if (b.getListing().getListing_id() == listing.getListing_id()) {
                    bookings.add(b);
                }
            }
        }
        owner.removeListing(listing, listings.findAll(), bookings);
        return listing;
    }
}
