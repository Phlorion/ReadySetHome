package com.example.readysethome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.readysethome.model.EmailAddress;
import com.example.readysethome.model.User;

// test
public class MainActivity extends AppCompatActivity {

    public static final User SYSTEM = new User("ReadySetHome", "Admin");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SYSTEM.setEmail(new EmailAddress("readysethomeAdmin@gmail.com"));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}