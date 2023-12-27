package com.example.readysethome.view.User.UserSignUp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.readysethome.R;

public class UserSignUpActivity extends AppCompatActivity implements UserSignUpView {

    /**
     * Δημιουργεί to layout και αρχικοποιεί
     * το activity.
     *
     * @param savedInstanceState το Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signup);

        final UserSignUpPresenter presenter = new UserSignUpPresenter();

        // make spinner for choosing account type
        Spinner dropdown = findViewById(R.id.accTypeSpinner);
        String[] items = new String[]{"Tenant", "Owner"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.activity_user_signup, items);
        dropdown.setAdapter(adapter);
    }
}
