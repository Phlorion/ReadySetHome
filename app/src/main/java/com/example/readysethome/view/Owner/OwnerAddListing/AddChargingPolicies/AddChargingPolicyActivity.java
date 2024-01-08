package com.example.readysethome.view.Owner.OwnerAddListing.AddChargingPolicies;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readysethome.R;
import com.example.readysethome.model.ChargingPolicy;

import java.util.ArrayList;

public class AddChargingPolicyActivity extends AppCompatActivity implements AddChargingPolicyView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_add_charging_policies);

        final AddChargingPolicyPresenter presenter = new AddChargingPolicyPresenter(AddChargingPolicyActivity.this);

        RecyclerView recyclerView = findViewById(R.id.charging_policies_recycler_view);
        ChargingPolicyAdaptor adapter = new ChargingPolicyAdaptor(this, presenter.getChargingPolicyModels());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // get Intent
        Intent intent = getIntent();
        if (intent.getSerializableExtra("EXISTING_CHARGING_POLICIES") != null) {
            presenter.setChargingPolicies((ArrayList<ChargingPolicy>) intent.getSerializableExtra("EXISTING_CHARGING_POLICIES"));
            presenter.setChargingPoliciesModels();
            adapter.setChargingModels(presenter.getChargingPolicyModels());
        }

        // add
        findViewById(R.id.add_new_charging_policy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view1 = LayoutInflater.from(AddChargingPolicyActivity.this).inflate(R.layout.alert_dialog_charging_policy, null);
                EditText start_ind = view1.findViewById(R.id.editTextText);
                EditText end_ind = view1.findViewById(R.id.editTextText3);
                EditText price_diff = view1.findViewById(R.id.editTextText4);
                MultiAutoCompleteTextView desc = view1.findViewById(R.id.multiAutoCompleteTextView);

                AlertDialog.Builder builder = new AlertDialog.Builder(AddChargingPolicyActivity.this);
                builder.setTitle("New Charging Policy");
                builder.setView(view1);
                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.addChargingPolicy(start_ind.getText().toString().trim(), end_ind.getText().toString().trim(), price_diff.getText().toString().trim(),
                                desc.getText().toString().trim());
                        adapter.setChargingModels(presenter.getChargingPolicyModels());
                        dialog.dismiss();
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
        findViewById(R.id.save_charging_policies).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if charging policies empty
                if (presenter.getChargingPolicies().size() == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddChargingPolicyActivity.this);
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

                int resultCode = 1;
                Intent resultIntent = new Intent();
                resultIntent.putExtra("CHARGING_POLICIES", presenter.getChargingPolicies());
                setResult(resultCode, resultIntent);
                finish();
            }
        });

        // cancel
        findViewById(R.id.cancel_charging_policies).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(AddChargingPolicyActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
