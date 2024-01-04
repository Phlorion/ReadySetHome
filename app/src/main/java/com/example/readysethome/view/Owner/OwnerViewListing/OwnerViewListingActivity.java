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

public class OwnerViewListingActivity extends AppCompatActivity implements OwnerViewListingView{

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_viewlisting);

        Intent intent = getIntent();
        int listing_id = intent.getIntExtra("LISTING_ID", 0);
        OwnerViewListingPresenter presenter = new OwnerViewListingPresenter(new ListingDAOMemory(), listing_id);
        presenter.setUpInfo();
        String title = getIntent().getStringExtra("TITLE");
        String desc = getIntent().getStringExtra("DESCRIPTION");
        String price = getIntent().getStringExtra("PRICE");
        int image_rsc = getIntent().getIntExtra("IMAGE", R.drawable.child_po);

        ((TextView)findViewById(R.id.owner_viewListing_title)).setText(title);
        ((TextView)findViewById(R.id.owner_viewListing_desc)).setText(desc);
        ((TextView)findViewById(R.id.owner_viewListing_price)).setText(price);
        ((ImageView)findViewById(R.id.owner_viewListing_image)).setImageResource(image_rsc);


        ((TextView)findViewById(R.id.owner_viewListing_incomePerMonthValue)).setText(Double.toString(presenter.getMonthlyIncome()));
        ((TextView)findViewById(R.id.owner_viewListing_bookingsPerMonthValue)).setText(Double.toString(presenter.getMonthlyBookings()));
        ((TextView)findViewById(R.id.owner_viewListing_cancelationsPerMonthValue)).setText(Double.toString(presenter.getMonthlyCancelations()));
        ((TextView)findViewById(R.id.owner_viewListing_ratingValue)).setText(Double.toString(presenter.getListing().getRating()));

    }
}
