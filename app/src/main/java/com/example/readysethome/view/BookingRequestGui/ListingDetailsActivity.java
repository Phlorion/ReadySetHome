package com.example.readysethome.view.BookingRequestGui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.readysethome.R;

public class ListingDetailsActivity extends AppCompatActivity {

    private EditText checkInEditText;
    private EditText checkOutEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing_details);

        checkInEditText = findViewById(R.id.check_in);
        checkOutEditText = findViewById(R.id.check_out);

        Button submitRequestButton = findViewById(R.id.submitRequest);

        submitRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered check-in and check-out times
                String checkInTime = checkInEditText.getText().toString();
                String checkOutTime = checkOutEditText.getText().toString();

                // TODO: check if the listing is available for that time period


                // TODO: Pass these values to BookingRequestActivity

                // Navigate to BookingRequestActivity when the Submit Request button is clicked
                Intent intent = new Intent(ListingDetailsActivity.this, BookingRequestActivity.class);


                intent.putExtra("checkInTime", checkInTime);
                intent.putExtra("checkOutTime", checkOutTime);

                startActivity(intent);
            }
        });
    }
}


