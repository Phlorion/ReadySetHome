package com.example.readysethome.view.Owner.OwnerViewListing;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.readysethome.R;
import com.example.readysethome.memorydao.ListingDAOMemory;
import com.example.readysethome.memorydao.TenantDAOMemory;
import com.example.readysethome.model.Listing;

public class OwnerViewListingActivity extends AppCompatActivity implements OwnerViewListingView {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_viewlisting);

        Intent intent = getIntent();
        int listing_id = intent.getIntExtra("LISTING_ID", 0);

        final OwnerViewListingPresenter presenter = new OwnerViewListingPresenter(OwnerViewListingActivity.this, new ListingDAOMemory(), new TenantDAOMemory(),listing_id);


        Spinner spinner = (Spinner) findViewById(R.id.owner_viewListing_selectMonthValue);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.months,
                android.R.layout.simple_spinner_item
        );
        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner.
        spinner.setAdapter(adapter);


        presenter.setUpBasicInfo();
        findViewById(R.id.owner_viewListing_submitYearMonth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.submitPressed();
            }
        });

        // delete listing
        findViewById(R.id.delete_listing_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Listing l = presenter.deleteListing();
                int resultCode = 99;
                Intent resultIntent = new Intent();
                resultIntent.putExtra("DELETED_LISTING", l.getListing_id());
                setResult(resultCode, resultIntent);
                finish();
            }
        });
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
    public void setOccupancy(String bookedDays) {
        ((TextView)findViewById(R.id.owner_viewListing_occupancyValue)).setText(bookedDays);
    }

    public String getYear() {
        return ((EditText)findViewById(R.id.owner_viewListing_selectYearValue)).getText().toString().trim();
    }

    public int getMonth() {
        return ((Spinner)findViewById(R.id.owner_viewListing_selectMonthValue)).getSelectedItemPosition()+1;
    }
}
