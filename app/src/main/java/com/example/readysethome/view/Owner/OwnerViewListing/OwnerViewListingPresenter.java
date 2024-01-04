package com.example.readysethome.view.Owner.OwnerViewListing;


import android.widget.TextView;
import android.view.View;

import com.example.readysethome.R;
import com.example.readysethome.dao.ListingDAO;
import com.example.readysethome.model.Listing;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class OwnerViewListingPresenter {
    private Listing listing;
    private double monthlyIncome;
    private int monthlyCancelations;
    private double monthlyBookings;

    public OwnerViewListingPresenter(ListingDAO listing, int id) {
        this.listing = listing.findByID(id);
    }

    public OwnerViewListingPresenter(OwnerViewListingPresenter presenter) {
        this.listing = presenter.listing;
        this.monthlyIncome = presenter.monthlyIncome;
        this.monthlyCancelations = presenter.monthlyCancelations;
        this.monthlyBookings = presenter.monthlyBookings;
    }

    public Listing getListing() {
        return listing;
    }

    public double getMonthlyIncome() {
        return monthlyIncome;
    }

    public int getMonthlyCancelations() {
        return monthlyCancelations;
    }

    public double getMonthlyBookings() {
        return monthlyBookings;
    }

    public void setUpInfo() {
        java.util.Calendar c1 = java.util.Calendar.getInstance();
        Date d1 = new Date();
        c1.set(java.util.Calendar.YEAR, 2023);
        c1.set(java.util.Calendar.MONTH, 5);
        c1.set(java.util.Calendar.DAY_OF_MONTH, 28);
        d1.setTime(c1.getTimeInMillis());

        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm");
        String yearmonth = dateFormat.format(d1);

        listing.calculateMonthlyIncome(d1);
        monthlyIncome = listing.getMonthlyIncome().get(yearmonth);
//        ((TextView) view.findViewById(R.id.owner_viewListing_incomePerMonthValue)).setText(String.valueOf(monthlyIncome));

        listing.calculateCancellationsPerMonth(d1);
        monthlyCancelations = listing.getMonthlyCancellations().get(yearmonth);
//        ((TextView) view.findViewById(R.id.owner_viewListing_cancelationsPerMonthValue)).setText(String.valueOf(monthlyCancelations));

        java.util.Calendar cal = java.util.Calendar.getInstance();
        monthlyBookings = listing.calculateOccupancy(cal);
//        ((TextView) view.findViewById(R.id.owner_viewListing_bookingsPerMonthValue)).setText(String.valueOf(monthlyBookings));
    }
}
