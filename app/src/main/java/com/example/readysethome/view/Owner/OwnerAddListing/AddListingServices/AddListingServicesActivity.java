package com.example.readysethome.view.Owner.OwnerAddListing.AddListingServices;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readysethome.R;
import com.example.readysethome.model.ChargingPolicy;
import com.example.readysethome.model.ListingsServices;
import com.example.readysethome.view.Owner.OwnerAddListing.AddChargingPolicies.AddChargingPolicyActivity;
import com.example.readysethome.view.Owner.OwnerAddListing.AddChargingPolicies.AddChargingPolicyPresenter;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AddListingServicesActivity extends AppCompatActivity implements AddListingServicesView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_add_listing_services);

        final AddListingServicesPresenter presenter = new AddListingServicesPresenter(AddListingServicesActivity.this);

        RecyclerView recyclerView = findViewById(R.id.listing_services_recycler_view);
        ListingServicesAdaptor adapter = new ListingServicesAdaptor(this, presenter.getListingServicesModel());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // get Intent
        Intent intent = getIntent();
        if (intent.getSerializableExtra("EXISTING_LISTING_SERVICES") != null) {
            presenter.setListingServices((ArrayList<ListingsServices>) intent.getSerializableExtra("EXISTING_LISTING_SERVICES"));
            presenter.setListingServicesModel();
            adapter.setListingServicesModels(presenter.setListingServicesModel());
        }

        // add
        findViewById(R.id.add_new_listing_service_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view1 = LayoutInflater.from(AddListingServicesActivity.this).inflate(R.layout.alert_dialog_listing_service, null);

                Spinner spinner = view1.findViewById(R.id.spinner);
                ArrayAdapter<CharSequence> spinner_adapter = ArrayAdapter.createFromResource(view1.getContext(), R.array.listing_service_types, android.R.layout.simple_spinner_item);
                spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(spinner_adapter);

                EditText price = view1.findViewById(R.id.editTextText5);

                AlertDialog.Builder builder = new AlertDialog.Builder(AddListingServicesActivity.this);
                builder.setTitle("New Listing Service");
                builder.setView(view1);
                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.addListingService(spinner.getSelectedItem().toString(), price.getText().toString().trim());
                        adapter.setListingServicesModels(presenter.setListingServicesModel());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.create();
                builder.show();

            }
        });

        // save
        findViewById(R.id.save_listing_services_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if charging policies empty
                if (presenter.getListingServices().size() == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddListingServicesActivity.this);
                    builder.setTitle("Empty list of charging policies.");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    builder.create();
                    builder.show();
                    return;
                }

                int resultCode = 270;
                Intent resultIntent = new Intent();
                System.out.println(presenter.getListingServices());
                resultIntent.putExtra("LISTING_SERVICES", presenter.getListingServices());
                setResult(resultCode, resultIntent);
                finish();
            }
        });

        // cancel
        findViewById(R.id.cancel_listing_services_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
