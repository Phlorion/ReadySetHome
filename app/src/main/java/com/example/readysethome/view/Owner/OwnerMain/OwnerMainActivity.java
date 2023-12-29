package com.example.readysethome.view.Owner.OwnerMain;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.readysethome.R;
import com.example.readysethome.memorydao.ListingDAOMemory;
import com.example.readysethome.view.Owner.OwnerHomeFragment;
import com.example.readysethome.view.Owner.OwnerPendingFragment;
import com.example.readysethome.view.Owner.OwnerProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class OwnerMainActivity extends AppCompatActivity implements OwnerMainView {

    BottomNavigationView navbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_main);

        Intent intent = getIntent();
        String user_id = intent.getStringExtra("user_id");

        final OwnerMainPresenter presenter = new OwnerMainPresenter(OwnerMainActivity.this, new ListingDAOMemory(), user_id);

        // create the fragments
        OwnerHomeFragment home = new OwnerHomeFragment(presenter);

        // when the owner main appears set the frame layout to the home fragment
        replaceFragment(home);

        navbar = findViewById(R.id.owner_main_bottomNavigationView);
        navbar.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.owner_nav_home) replaceFragment(home);
            else if (item.getItemId() == R.id.owner_nav_pending) replaceFragment(new OwnerPendingFragment());
            else replaceFragment(new OwnerProfileFragment());
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
        fragmentTransaction.replace(R.id.owner_main_framelayout, fragment);
        fragmentTransaction.commit();
    }
}
