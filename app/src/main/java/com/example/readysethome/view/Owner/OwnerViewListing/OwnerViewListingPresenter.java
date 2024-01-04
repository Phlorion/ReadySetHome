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
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm");
        String yearmonth = dateFormat.format(date);

        listing.calculateMonthlyIncome(date);
        monthlyIncome = listing.getMonthlyIncome().get(yearmonth);
//        ((TextView) view.findViewById(R.id.owner_viewListing_incomePerMonthValue)).setText(String.valueOf(monthlyIncome));

        listing.calculateCancellationsPerMonth(date);
        monthlyCancelations = listing.getMonthlyCancellations().get(yearmonth);
//        ((TextView) view.findViewById(R.id.owner_viewListing_cancelationsPerMonthValue)).setText(String.valueOf(monthlyCancelations));

        java.util.Calendar cal = java.util.Calendar.getInstance();
        monthlyBookings = listing.calculateOccupancy(cal);
//        ((TextView) view.findViewById(R.id.owner_viewListing_bookingsPerMonthValue)).setText(String.valueOf(monthlyBookings));
    }
}
