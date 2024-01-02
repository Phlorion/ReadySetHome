package com.example.readysethome.view.BookingRequestGui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

import com.example.readysethome.R;
import com.example.readysethome.dao.ListingDAO;
import com.example.readysethome.memorydao.ListingDAOMemory;
import com.example.readysethome.model.Listing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ListingDetailsActivity extends AppCompatActivity implements ListingDetailsView {

    private EditText checkInEditText;
    private EditText checkOutEditText;

    private int listingid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing_details);

        checkInEditText = findViewById(R.id.check_in);
        checkOutEditText = findViewById(R.id.check_out);


        final ListingDetailsPresenter presenter = new ListingDetailsPresenter(this, new ListingDAOMemory() {
        });

        Button submitRequestButton = findViewById(R.id.submitRequest);

        submitRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSubmission(presenter);
            }
        });
    }

    private void handleSubmission(ListingDetailsPresenter presenter) {
        try{
        // Get the entered check-in and check-out times
        Date checkInTime = presenter.parseDate(checkInEditText.getText().toString().trim());
        Date checkOutTime = presenter.parseDate(checkOutEditText.getText().toString().trim());

        // Check listing availability
        if (presenter.isListingAvailable(getListingId(),checkInTime, checkOutTime)) {
            // Navigate to BookingRequestActivity when the Submit Request button is clicked
            Intent intent = new Intent(ListingDetailsActivity.this, BookingRequestActivity.class);
            intent.putExtra("checkInTime", checkInTime);
            intent.putExtra("checkOutTime", checkOutTime);
            startActivity(intent);
        } else {
            // Show availability error
            //TODO: logika san to login tha einai
        }
        }catch(ParseException e){
            e.printStackTrace();
            }
    }


    @Override
    public String getCheckIn() {
        return checkInEditText.getText().toString().trim();
    }

    @Override
    public String getCheckOut() {
        return checkOutEditText.getText().toString().trim();
    }

    @Override
    public int getListingId() {
        return listingid;
    }


}
