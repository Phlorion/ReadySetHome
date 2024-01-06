package com.example.readysethome.view.Tenant.TenantMain;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.readysethome.R;
import com.example.readysethome.memorydao.ListingDAOMemory;
import com.example.readysethome.memorydao.TenantDAOMemory;
import com.example.readysethome.view.Tenant.TenantBookingsFragment;
import com.example.readysethome.view.Tenant.TenantHomeFragment;
import com.example.readysethome.view.Tenant.TenantNotificationsFragment;
import com.example.readysethome.view.Tenant.TenantProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TenantMainActivity extends AppCompatActivity implements TenantMainView {

    TenantMainPresenter presenter;
    BottomNavigationView navbar;
    TenantHomeFragment home;
    TenantBookingsFragment bookings;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant_main);

        Intent intent = getIntent();
        String user_id = intent.getStringExtra("user_id");

        presenter = new TenantMainPresenter(TenantMainActivity.this, new ListingDAOMemory(), new TenantDAOMemory(), user_id);

        // create the fragments
        home = new TenantHomeFragment(presenter);
        // create the fragments
        bookings = new TenantBookingsFragment(presenter);
        // when the owner main appears set the frame layout to the home fragment
        replaceFragment(home);

        navbar = findViewById(R.id.tenant_main_bottomNavigationView);
        navbar.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.tenant_nav_home) replaceFragment(home);
            else if (item.getItemId() == R.id.tenant_nav_bookings) replaceFragment(bookings);
            else if (item.getItemId() == R.id.tenant_nav_notifications) replaceFragment(new TenantNotificationsFragment());
            else replaceFragment(new TenantProfileFragment());
            return true;
        });

    }

    /**
     * Αλλαγή του fragment
     * @param fragment Το fragment στο οποίο θέλουμε να αλλάξουμε
     */
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.tenant_main_framelayout, fragment);
        fragmentTransaction.commit();
    }

    public TenantHomeFragment getHomeFrag() {
        return home;
    }

    public TenantBookingsFragment getBookingsFrag() {
        return bookings;
    }
}
