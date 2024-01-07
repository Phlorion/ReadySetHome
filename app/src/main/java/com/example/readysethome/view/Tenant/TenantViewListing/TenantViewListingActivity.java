package com.example.readysethome.view.Tenant.TenantViewListing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.readysethome.R;
import com.example.readysethome.memorydao.ListingDAOMemory;
import com.example.readysethome.memorydao.TenantDAOMemory;
import com.example.readysethome.model.Listing;
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
    TextView ownerNameTextView;
    TextView bathroomsTextView;
    TextView wifiTextView;
    TextView balconyTextView;
    TextView bedroomsTextView;
    TextView kitchensTextView;
    TextView floorTextView;
    TenantViewListingPresenter presenter = null;

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


        //ownerNameTextView = findViewById(R.id.ownerName);
        //bathroomsTextView = findViewById(R.id.bathroomsTextView);
       // wifiTextView = findViewById(R.id.wifiTextView);
        //balconyTextView = findViewById(R.id.BalconyTextView);
       // bedroomsTextView = findViewById(R.id.listingBedrooms);
      //  kitchensTextView = findViewById(R.id.kitchensTextView);
       // floorTextView = findViewById(R.id.floorTextView);

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
    public void setOwnerName(String ownerName) {
        ownerNameTextView.setText(ownerName);
    }

    @Override
    public void setBathrooms(String bathrooms) {
        bathroomsTextView.setText(bathrooms);
    }

    @Override
    public void setWifi(Boolean wifi) {
        String wifiAns = wifi ? "Yes" : "No";
        if (wifiTextView != null) {
            wifiTextView.setText(wifiAns);
        } else {
            System.out.println("wifiTextView is null");
        }
    }

    @Override
    public void setBalcony(Boolean balcony) {
        String balconyAns="No";
        if(balcony){
            balconyAns="Yes";
        }
        balconyTextView.setText(balconyAns);
    }

    @Override
    public void setBedrooms(String bedrooms) {
        bedroomsTextView.setText(bedrooms);
    }

    @Override
    public void setKitchens(String kitchens) {
        kitchensTextView.setText(kitchens);
    }

    @Override
    public void setFloor(String floor) {
        floorTextView.setText(floor);
    }

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
