// BookingRequestActivity.java
package com.example.readysethome.view.BookingRequestGui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import com.example.readysethome.R;
import com.example.readysethome.memorydao.ListingDAOMemory;
import com.example.readysethome.memorydao.TenantDAOMemory;
import com.example.readysethome.model.BookingRequest;
import com.example.readysethome.view.Tenant.TenantBookingModel;

import java.util.ArrayList;
import java.util.Date;

public class BookingRequestActivity extends AppCompatActivity implements BookingRequestView {

    private TextView paymentAmountTextView;
    private TextView depositAmountTextView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_request_confrimation);

        paymentAmountTextView = findViewById(R.id.paymentAmountTextView);
        depositAmountTextView = findViewById(R.id.depositAmountTextView);
        String tenant_id=null;
        Date checkIn = null;
        Date checkOut = null;
        int listing_id = 0;


        Intent intent = getIntent();
        if (intent != null) {
            checkIn = (Date) intent.getSerializableExtra("checkInTime");
            checkOut = (Date) intent.getSerializableExtra("checkOutTime");
            listing_id = intent.getIntExtra("listing_id", 0);
            tenant_id=intent.getStringExtra("tenant_id");
        }

        final BookingRequestPresenter presenter = new BookingRequestPresenter(this, checkIn, checkOut, new ListingDAOMemory(), listing_id,tenant_id,new TenantDAOMemory());

        // calculate amount of pay and deposit
        double payment = presenter.calculatePaymentAmount();

        Button confirmSubmitButton = findViewById(R.id.confirmandsubmitBookingButton);
        confirmSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onSubmitBookingRequest();

            }
        });

        Button cancellationBtn = findViewById(R.id.cancellationButton);
        cancellationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onCancelBookingRequest();
            }
        });
    }

    @Override
    public void setPaymentAmount(String paymentAmount) {
        paymentAmountTextView.setText(paymentAmount);
    }

    @Override
    public void setDepositAmount(String depositAmount) {
        depositAmountTextView.setText(depositAmount);
    }

    @Override
    public void confirm(Date checkin,Date checkout,int listingid,String tenant_id) {
        Intent intent = new Intent(BookingRequestActivity.this, BookingConfirmationActivity.class);
        intent.putExtra("checkIn", checkin);
        intent.putExtra("checkOut", checkout);
        intent.putExtra("listing_id",listingid);
        intent.putExtra("tenant_id",tenant_id);
        startActivity(intent);
        finish();
    }

    @Override
    public void cancel(String tenant_id) {
        Toast.makeText(BookingRequestActivity.this, "Canceled booking request.", Toast.LENGTH_SHORT).show();
        finish();

    }

    @Override
    public void insufficientFunds(String tenant_id) {
        Toast.makeText(BookingRequestActivity.this, "Insufficient Funds", Toast.LENGTH_SHORT).show();
        finish();
    }
}
