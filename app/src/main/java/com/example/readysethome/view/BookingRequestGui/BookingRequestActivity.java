// BookingRequestActivity.java
package com.example.readysethome.view.BookingRequestGui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.readysethome.R;
import com.example.readysethome.view.HomePage.HomePageActivity;

public class BookingRequestActivity extends AppCompatActivity implements BookingRequestView {

    private TextView paymentAmountTextView;
    private TextView depositAmountTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_request_confrimation);

        final BookingRequestPresenter presenter = new BookingRequestPresenter(this);

        paymentAmountTextView = findViewById(R.id.paymentAmountTextView);
        depositAmountTextView = findViewById(R.id.depositAmountTextView);


        Button confirmSubmitButton = findViewById(R.id.confirmandsubmitBookingButton);
        confirmSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(BookingRequestActivity.this, BookingConfirmationActivity.class);
                startActivity(intent);
            }
        });


        TextView cancellationTextView = findViewById(R.id.cancellationTextView);
        cancellationTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to HomePageActivity when the cancellation TextView is clicked
                Intent intent = new Intent(BookingRequestActivity.this, HomePageActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void displayBookingConfirmation() {

    }

    @Override
    public void displayCancellationConfirmation() {

    }

    @Override
    public void updatePaymentAndDepositAmounts(double paymentAmount) {

        TextView paymentAmountTextView = findViewById(R.id.paymentAmountTextView);
        TextView depositAmountTextView = findViewById(R.id.depositAmountTextView);


        paymentAmountTextView.setText(paymentAmount +"$");
        depositAmountTextView.setText(paymentAmount*0.2 +"$");
    }


}
