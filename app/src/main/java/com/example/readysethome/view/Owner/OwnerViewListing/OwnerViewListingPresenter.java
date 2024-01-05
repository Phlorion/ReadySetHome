package com.example.readysethome.view.Owner.OwnerViewListing;


import android.annotation.SuppressLint;
import android.widget.TextView;
import android.view.View;

import com.example.readysethome.R;
import com.example.readysethome.dao.ListingDAO;
import com.example.readysethome.model.Listing;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class OwnerViewListingPresenter {
    private OwnerViewListingView view;
    private ListingDAO listings;
    private Listing listing;

    public OwnerViewListingPresenter(OwnerViewListingView view, ListingDAO listings, int id) {
        this.view = view;
        this.listings = listings;
        listing = listings.findByID(id);
    }

    public Listing getListing() {
        return listing;
    }

    public void setUpInfo() {
        view.setTitle(listing.getTitle());
        view.setDesc(listing.getDescription());
        view.setPrice(Double.toString(listing.getPrice()));
        if (listing.getPhotos() != null)
            view.setImage(listing.getPhotos()[0]);
        else
            view.setImage(R.drawable.child_po);

        Calendar c1 = Calendar.getInstance();
        Date d1;
        c1.set(Calendar.YEAR, 2023);
        c1.set(Calendar.MONTH, 5);
        c1.set(Calendar.DAY_OF_MONTH, 28);
        c1.set(Calendar.HOUR_OF_DAY, 0);
        c1.set(Calendar.MINUTE, 0);
        c1.set(Calendar.SECOND, 0);
        d1 = c1.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        String yearmonth = dateFormat.format(d1);

        if (listing.getMonthlyIncome().get(yearmonth) != null) {
            double monthlyIncome = listing.getMonthlyIncome().get(yearmonth);
            view.setIncomePerMonth(Double.toString(monthlyIncome));
        }

        view.setCancellationsPerMonth("0");
        view.setBookingsPerMonth("0");
        view.setRating("0");
    }
}
