package com.example.readysethome.view.Tenant.TenantViewListing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.readysethome.R;
import com.example.readysethome.memorydao.ListingDAOMemory;
import com.example.readysethome.view.BookingRequestGui.BookingRequestActivity;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class TenantViewListingActivity extends AppCompatActivity implements TenantViewListingView, DatePickerDialog.OnDateSetListener {

    Button checkInBtn;
    Button checkOutBtn;
    TextView checkInDisplay;
    TextView checkOutDisplay;
    boolean checkInPressed;
    TenantViewListingPresenter presenter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing_details);

        Intent intent = getIntent();
        int listing_id = intent.getIntExtra("LISTING_ID", 0);

        presenter = new TenantViewListingPresenter(TenantViewListingActivity.this, new ListingDAOMemory(), listing_id);

        checkInBtn = findViewById(R.id.checkInBtn);
        checkInDisplay = findViewById(R.id.checkInDisplay);
        checkOutBtn = findViewById(R.id.checkOutBtn);
        checkOutDisplay = findViewById(R.id.checkOutDisplay);
        checkInPressed = false;

        // check in pressed
        checkInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInPressed = true;
                // create dpd
                com.wdullaer.materialdatetimepicker.date.DatePickerDialog dpd = presenter.createDPD();
                // disable unavailable days
                presenter.setAvailableInCalendar(dpd, checkInPressed);

                dpd.show(getSupportFragmentManager(), "CheckIn");
            }
        });

        // check out pressed
        checkOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create dpd
                com.wdullaer.materialdatetimepicker.date.DatePickerDialog dpd = presenter.createDPD();
                // disable unavailable days
                presenter.setAvailableInCalendar(dpd, checkInPressed);

                dpd.show(getSupportFragmentManager(), "CheckIn");
            }
        });

        Button submitRequestButton = findViewById(R.id.submitRequest);

        submitRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.handleSubmission();
            }
        });
    }

    @Override
    public String getCheckInTV() {
        return checkInDisplay.getText().toString();
    }

    @Override
    public String getCheckOutTV() {
        return checkOutDisplay.getText().toString();
    }

    @Override
    public void setCheckInTV(String checkIn) {
        checkInDisplay.setText(checkIn);
    }

    @Override
    public void setCheckOutTV(String checkOut) {
        checkOutDisplay.setText(checkOut);
    }

    @Override
    public void submit(Date checkInTime, Date checkOutTime) {
        Intent intent = new Intent(TenantViewListingActivity.this, BookingRequestActivity.class);
        intent.putExtra("checkInTime", checkInTime);
        intent.putExtra("checkOutTime", checkOutTime);
        startActivity(intent);
    }

    @Override
    public void showErrorMessage(String error) {
        Toast.makeText(TenantViewListingActivity.this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, monthOfYear);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String date = DateFormat.getDateInstance().format(c.getTime());
        if (checkInPressed) {
            try {
                presenter.setCheckIn(date);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            checkInPressed = false;
        } else {
            try {
                presenter.setCheckOut(date);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
