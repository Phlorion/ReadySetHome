package com.example.readysethome.view.Owner.OwnerViewListing;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.readysethome.R;
import com.example.readysethome.dao.ListingDAO;
import com.example.readysethome.model.Listing;

public class OwnerViewListingPresenter {
    private Listing listing;

    public OwnerViewListingPresenter(ListingDAO listing, int id) {
        this.listing = listing.findByID(id);
    }

    public void setUpInfo() {


    }
}
