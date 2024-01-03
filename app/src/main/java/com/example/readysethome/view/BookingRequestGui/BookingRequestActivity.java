// BookingRequestActivity.java
package com.example.readysethome.view.BookingRequestGui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.readysethome.R;
import com.example.readysethome.memorydao.ListingDAOMemory;
import com.example.readysethome.view.HomePage.HomePageActivity;


import java.util.Date;

public class BookingRequestActivity extends AppCompatActivity implements BookingRequestView {

    private TextView paymentAmountTextView;
    private TextView depositAmountTextView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_request_confrimation);

        paymentAmountTextView = findViewById(R.id.paymentAmountTextView);
        depositAmountTextView = findViewById(R.id.depositAmountTextView);

        Date checkIn = null;
        Date checkOut = null;
        int listing_id = 0;

        Intent intent = getIntent();
        if (intent != null) {
            checkIn = (Date) intent.getSerializableExtra("checkInTime");
            checkOut = (Date) intent.getSerializableExtra("checkOutTime");
            listing_id = intent.getIntExtra("listing_id", 0);
        }

        final BookingRequestPresenter presenter = new BookingRequestPresenter(this, checkIn, checkOut, new ListingDAOMemory(), listing_id);

        // calculate amount of pay and deposit
        double payment = presenter.calculatePaymentAmount();

        Button confirmSubmitButton = findViewById(R.id.confirmandsubmitBookingButton);
        confirmSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onSubmitBookingRequest(payment);
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
    public void confirm(double payment) {
        Intent intent = new Intent(BookingRequestActivity.this, BookingConfirmationActivity.class);
        intent.putExtra("PAY", payment);
        intent.putExtra("UPFRONT", 0.2 * payment);
        startActivity(intent);
    }

    @Override
    public void cancel() {
        Toast.makeText(BookingRequestActivity.this, "Canceled booking request.", Toast.LENGTH_SHORT).show();
        finish();
    }
}
