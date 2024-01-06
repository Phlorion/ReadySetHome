package com.example.readysethome.view.BookingRequestGui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.readysethome.R;
import com.example.readysethome.memorydao.ListingDAOMemory;
import com.example.readysethome.memorydao.TenantDAOMemory;
import com.example.readysethome.view.Tenant.TenantHomeFragment;
import com.example.readysethome.view.Tenant.TenantMain.TenantMainActivity;

import java.util.Date;

public class BookingConfirmationActivity extends AppCompatActivity implements BookingConfirmationView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_request_confirmation_pressed);
        Date checkIn = null;
        Date checkOut = null;
        int listing_id = 0;
        String tenant_id=null;
        Intent intent = getIntent();
       if (intent != null) {
            checkIn = (Date) intent.getSerializableExtra("checkIn");
            checkOut = (Date) intent.getSerializableExtra("checkOut");
            listing_id = intent.getIntExtra("listing_id", 0);
            tenant_id = intent.getStringExtra("tenant_id");
        }

        final BookingConfirmationPresenter presenter = new BookingConfirmationPresenter(this,new ListingDAOMemory(),checkIn,checkOut,listing_id,tenant_id,new TenantDAOMemory());

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
        ((TextView)findViewById(R.id.textView3)).setText(message);
    }

    @Override
    public void navigateToHomePage(String tenant_id) {
        Intent intent = new Intent(BookingConfirmationActivity.this, TenantMainActivity.class);
        intent.putExtra("user_id",tenant_id);
        startActivity(intent);

    }
}
