package com.example.readysethome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.readysethome.model.CreditCard;
import com.example.readysethome.model.EmailAddress;
import com.example.readysethome.model.Password;
import com.example.readysethome.model.User;

import java.util.Date;

// test
public class MainActivity extends AppCompatActivity {

    public static final User SYSTEM = new User("ReadySetHome", "Admin", new EmailAddress("readysethomeAdmin@gmail.com"),
    new Password("admin"), new CreditCard("0000000000000000"), new Date());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}