package com.example.readysethome.view.Tenant.TenantViewListing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.readysethome.R;
import com.example.readysethome.memorydao.ListingDAOMemory;
import com.example.readysethome.view.BookingRequestGui.BookingRequestActivity;

import java.util.Date;

public class TenantViewListingActivity extends AppCompatActivity implements TenantViewListingView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing_details);

        Intent intent = getIntent();
        int listing_id = intent.getIntExtra("LISTING_ID", 0);

        final TenantViewListingPresenter presenter = new TenantViewListingPresenter(TenantViewListingActivity.this, new ListingDAOMemory(), listing_id);

        Button submitRequestButton = findViewById(R.id.submitRequest);

        submitRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.handleSubmission();
            }
        });
    }

    @Override
    public String getCheckIn() {
        return ((EditText)findViewById(R.id.check_in)).getText().toString().trim();
    }

    @Override
    public String getCheckOut() {
        return ((EditText)findViewById(R.id.check_out)).getText().toString().trim();
    }

    @Override
    public void submit(Date checkInTime, Date checkOutTime) {
        Intent intent = new Intent(TenantViewListingActivity.this, BookingRequestActivity.class);
        intent.putExtra("checkInTime", checkInTime);
        intent.putExtra("checkOutTime", checkOutTime);
        startActivity(intent);
    }
}
