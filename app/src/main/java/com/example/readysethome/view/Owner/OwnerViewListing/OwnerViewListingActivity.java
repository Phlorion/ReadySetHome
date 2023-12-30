package com.example.readysethome.view.Owner.OwnerViewListing;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.readysethome.R;

public class OwnerViewListingActivity extends AppCompatActivity implements OwnerViewListingView{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_viewlisting);

        String title = getIntent().getStringExtra("TITLE");
        String desc = getIntent().getStringExtra("DESCRIPTION");
        String price = getIntent().getStringExtra("PRICE");
        int image_rsc = getIntent().getIntExtra("IMAGE", R.drawable.child_po);

        ((TextView)findViewById(R.id.owner_viewlisting_title)).setText(title);
        ((TextView)findViewById(R.id.owner_viewlisting_desc)).setText(desc);
        ((TextView)findViewById(R.id.owner_viewlisting_price)).setText(price);
        ((ImageView)findViewById(R.id.owner_viewlisting_image)).setImageResource(image_rsc);

    }
}
