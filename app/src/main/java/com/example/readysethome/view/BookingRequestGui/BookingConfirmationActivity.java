package com.example.readysethome.view.BookingRequestGui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.readysethome.R;

public class BookingConfirmationActivity extends AppCompatActivity implements BookingConfirmationView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_request_confirmation_pressed);

        final BookingConfirmationPresenter presenter = new BookingConfirmationPresenter(this);

        Button homeButton = findViewById(R.id.ReturnToMainMenu);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onHomePageClicked();
            }
        });

        // Initialize the presenter
        presenter.onViewCreated();
    }

    @Override
    public void displayConfirmationMessage(String message) {

    }

    @Override
    public void navigateToHomePage() {
        // Navigate to the homepage activity
        finish(); // Close the current activity
    }
}
