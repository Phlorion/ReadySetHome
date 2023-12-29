package com.example.readysethome.view.HomePage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.readysethome.R;
import com.example.readysethome.dao.Initializer;
import com.example.readysethome.memorydao.MemoryInitializer;
import com.example.readysethome.view.User.UserLogIn.UserLoginActivity;
import com.example.readysethome.view.User.UserSignUp.UserSignUpActivity;

public class HomePageActivity extends AppCompatActivity implements HomePageView {

    /**
     * Δημιουργεί to layout και αρχικοποιεί
     * το activity.
     *
     * @param savedInstanceState το Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        final HomePagePresenter presenter = new HomePagePresenter(this);
        Initializer dataHelper = new MemoryInitializer();
        dataHelper.prepareData();

        // user login
        findViewById(R.id.loginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onUserLogIn();
            }
        });

        // user signup
        findViewById(R.id.signupButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onUserSignUp();
            }
        });
    }

    /**
     * Όταν πραγματοποιείται click στο UserLogIn activity
     * ο χρήστης μεταφέρεται σε αυτό από την αρχική σελίδα.
     */
    public void login() {
        Intent intent = new Intent(HomePageActivity.this, UserLoginActivity.class);
        startActivity(intent);
    }

    /**
     * Όταν πραγματοποιείται click στο UserSignUp activity
     * ο χρήστης μεταφέρεται σε αυτό από την αρχική σελίδα.
     */
    public void signup() {
        Intent intent = new Intent(HomePageActivity.this, UserSignUpActivity.class);
        startActivity(intent);
    }

}