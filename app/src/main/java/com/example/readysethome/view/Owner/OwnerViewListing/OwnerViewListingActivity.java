package com.example.readysethome.view.Owner.OwnerViewListing;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.readysethome.R;
import com.example.readysethome.memorydao.ListingDAOMemory;

public class OwnerViewListingActivity extends AppCompatActivity implements OwnerViewListingView {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_viewlisting);

        Intent intent = getIntent();
        int listing_id = intent.getIntExtra("LISTING_ID", 0);

        final OwnerViewListingPresenter presenter = new OwnerViewListingPresenter(OwnerViewListingActivity.this, new ListingDAOMemory(), listing_id);
        presenter.setUpInfo();
    }

    @Override
    public void setTitle(String title) {
        ((TextView)findViewById(R.id.owner_viewListing_title)).setText(title);
    }

    @Override
    public void setDesc(String desc) {
        ((TextView)findViewById(R.id.owner_viewListing_desc)).setText(desc);
    }

    @Override
    public void setPrice(String price) {
        ((TextView)findViewById(R.id.owner_viewListing_price)).setText(price);
    }

    @Override
    public void setImage(int img) {
        ((ImageView)findViewById(R.id.owner_viewListing_image)).setImageResource(img);
    }

    @Override
    public void setIncomePerMonth(String income) {
        ((TextView)findViewById(R.id.owner_viewListing_incomePerMonthValue)).setText(income);
    }

    @Override
    public void setCancellationsPerMonth(String cancellations) {
        ((TextView)findViewById(R.id.owner_viewListing_cancelationsPerMonthValue)).setText(cancellations);
    }

    @Override
    public void setBookingsPerMonth(String bookings) {
        ((TextView)findViewById(R.id.owner_viewListing_bookingsPerMonthValue)).setText(bookings);
    }

    @Override
    public void setRating(String rating) {
        ((TextView)findViewById(R.id.owner_viewListing_ratingValue)).setText(rating);
    }
}
