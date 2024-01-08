package com.example.readysethome.view.Tenant.TenantViewListing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.readysethome.R;
import com.example.readysethome.memorydao.ListingDAOMemory;
import com.example.readysethome.memorydao.TenantDAOMemory;
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

    TextView floorDisplay;
    TextView WifiDisplay;
    boolean checkInPressed;
    TenantViewListingPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing_details);


        Intent intent = getIntent();
        int listing_id = intent.getIntExtra("LISTING_ID", 0);
        String tenant_id=intent.getStringExtra("tenant_id");

        presenter = new TenantViewListingPresenter(TenantViewListingActivity.this, new ListingDAOMemory(), listing_id,tenant_id,new TenantDAOMemory());

        checkInBtn = findViewById(R.id.checkInBtn);
        checkOutBtn = findViewById(R.id.checkOutBtn);
        checkInDisplay = findViewById(R.id.textView21);
        checkOutDisplay = findViewById(R.id.textView22);


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
    public void setListingTitle(String title) {

        ((TextView)findViewById(R.id.listingtitle)).setText(title);
    }

    @Override
    public void setListingDesc(String desc) {
        ((TextView)findViewById(R.id.listingdesc)).setText(desc);
    }

    @Override
    public void setListingPrice(String price) {
        ((TextView)findViewById(R.id.listingprice)).setText(price);
    }

    @Override
    public void setListingLocation(String location) {
        ((TextView)findViewById(R.id.listingLocation)).setText(location);
    }

    @Override
    public void setListingSize(String size) {
        ((TextView)findViewById(R.id.listingsize)).setText(size);
    }
    @Override
    public void setListingFloor(String floor) {

        ((TextView)findViewById(R.id.listingfloor)).setText("Floor: "+floor);
    }
    public void setListingWifi(boolean wifi) {
       String Str_wifi=wifi?"Wifi:yes":"Wifi:no";
        ((TextView)findViewById(R.id.listingwifi)).setText(Str_wifi);
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
    public void setFloorTV(String floor){System.out.println(floor);floorDisplay.setText(floor);}

    @Override
    public void submit(Date checkInTime, Date checkOutTime, int listing_id,String tenant_id) {
        Intent intent = new Intent(TenantViewListingActivity.this, BookingRequestActivity.class);
        intent.putExtra("checkInTime", checkInTime);
        intent.putExtra("checkOutTime", checkOutTime);
        intent.putExtra("listing_id", listing_id);
        intent.putExtra("tenant_id",tenant_id);
        startActivity(intent);
        finish();
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
