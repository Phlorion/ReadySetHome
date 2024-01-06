package com.example.readysethome.view.Owner.OwnerAddListing;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.readysethome.R;
import com.example.readysethome.dao.ListingDAO;
import com.example.readysethome.dao.OwnerDAO;
import com.example.readysethome.memorydao.ListingDAOMemory;
import com.example.readysethome.memorydao.OwnerDAOMemory;
import com.example.readysethome.model.ChargingPolicy;
import com.example.readysethome.model.Listing;
import com.example.readysethome.model.Owner;
import com.example.readysethome.view.Owner.OwnerAddListing.AddChargingPolicies.AddChargingPolicyActivity;

import java.util.ArrayList;

public class OwnerAddListingActivity extends AppCompatActivity implements OwnerAddListingView {

    OwnerAddListingPresenter presenter;
    Listing listing;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_add_listing);

        /**
         * Παίρνουμε το αποτέλεσμα ενός activity
         */
        ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult o) {
                if (o != null && o.getResultCode() == 1) {
                    if (o.getData() != null) {
                        presenter.addChargingPolicy((ArrayList<ChargingPolicy>) o.getData().getSerializableExtra("CHARGING_POLICIES"));
                    }
                }
            }
        });

        String owner_id = getIntent().getStringExtra("OWNER");
        //System.out.println("------------------------"+owner+"---------------------------");
        presenter = new OwnerAddListingPresenter(OwnerAddListingActivity.this, new ListingDAOMemory(), new OwnerDAOMemory(), owner_id);

        // add charging policy
        findViewById(R.id.owner_add_charging_policy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnerAddListingActivity.this, AddChargingPolicyActivity.class);
                // if charging policies not null put Extra
                if (presenter.getChargingPolicies() != null && presenter.getChargingPolicies().size() > 0) {
                    intent.putExtra("EXISTING_CHARGING_POLICIES", presenter.getChargingPolicies());
                }
                startForResult.launch(intent);
            }
        });


        // get add-button and add event listener
        findViewById(R.id.owner_add_listing_addBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listing = presenter.addListing();

                int resultCode = 0;
                Intent resultIntent = new Intent();
                resultIntent.putExtra("NEW_LISTING", listing.getListing_id());
                setResult(resultCode, resultIntent);
                finish();
            }
        });
    }

    @Override
    public void successfullyFinishActivity(String message) {
        Toast.makeText(OwnerAddListingActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(OwnerAddListingActivity.this);
        builder.setCancelable(false);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public String getCity() {
        return ((EditText)findViewById(R.id.owner_add_listing_city)).getText().toString().trim();
    }

    @Override
    public String getStreet() {
        return ((EditText)findViewById(R.id.owner_add_listing_street)).getText().toString().trim();
    }

    @Override
    public String getAdrNumber() {
        return ((EditText)findViewById(R.id.owner_add_listing_addrNum)).getText().toString().trim();
    }

    @Override
    public String getFloor() {
        return ((EditText)findViewById(R.id.owner_add_listing_floor)).getText().toString().trim();
    }

    @Override
    public String getApSize() {
        return ((EditText)findViewById(R.id.owner_add_listing_ap_size)).getText().toString().trim();
    }

    @Override
    public boolean getWifi() {
        return ((CheckBox)findViewById(R.id.owner_add_listing_wifi)).isChecked();
    }

    @Override
    public boolean getLivingRoom() {
        return ((CheckBox)findViewById(R.id.owner_add_listing_living)).isChecked();
    }

    @Override
    public boolean getBalcony() {
        return ((CheckBox)findViewById(R.id.owner_add_listing_balcony)).isChecked();
    }

    @Override
    public String getBathSize() {
        return ((EditText)findViewById(R.id.owner_add_listing_bath_size)).getText().toString().trim();
    }

    @Override
    public boolean getBathShower() {
        return ((CheckBox)findViewById(R.id.owner_add_listing_bath_shower)).isChecked();
    }

    @Override
    public boolean getBathToilet() {
        return ((CheckBox)findViewById(R.id.owner_add_listing_bath_toilet)).isChecked();
    }

    @Override
    public boolean getHairdryer() {
        return ((CheckBox)findViewById(R.id.owner_add_listing_bath_hairdryer)).isChecked();
    }

    @Override
    public String getKitchenSize() {
        return ((EditText)findViewById(R.id.owner_add_listing_kitch_size)).getText().toString().trim();
    }

    @Override
    public boolean getKitchenOven() {
        return ((CheckBox)findViewById(R.id.owner_add_listing_kitch_oven)).isChecked();
    }

    @Override
    public boolean getKitchenMicrowave() {
        return ((CheckBox)findViewById(R.id.owner_add_listing_kitch_micro)).isChecked();
    }

    @Override
    public boolean getKitchenRefrigerator() {
        return ((CheckBox)findViewById(R.id.owner_add_listing_kitch_refr)).isChecked();
    }

    @Override
    public boolean getKitchenToaster() {
        return ((CheckBox)findViewById(R.id.owner_add_listing_kitch_toast)).isChecked();
    }

    @Override
    public boolean getKitchenCoffee() {
        return ((CheckBox)findViewById(R.id.owner_add_listing_kitch_coffee)).isChecked();
    }

    @Override
    public boolean getKitchenDiningTable() {
        return ((CheckBox)findViewById(R.id.owner_add_listing_kitch_dining)).isChecked();
    }

    @Override
    public String getBedroomSize() {
        return ((EditText)findViewById(R.id.owner_add_listing_bed_size)).getText().toString().trim();
    }

    @Override
    public String getBedroomDoubleBeds() {
        return ((EditText)findViewById(R.id.owner_add_listing_bed_double)).getText().toString().trim();
    }

    @Override
    public String getBedroomSingleBeds() {
        return ((EditText)findViewById(R.id.owner_add_listing_bed_single)).getText().toString().trim();
    }

    @Override
    public boolean getBedroomTV() {
        return ((CheckBox)findViewById(R.id.owner_add_listing_bed_tv)).isChecked();
    }

    @Override
    public String getListingTitle() {
        return ((EditText)findViewById(R.id.owner_add_listing_title)).getText().toString().trim();
    }

    @Override
    public String getListingDescription() {
        return ((EditText)findViewById(R.id.owner_add_listing_desc)).getText().toString().trim();
    }

    @Override
    public String getListingPrice() {
        return ((EditText)findViewById(R.id.owner_add_listing_price)).getText().toString().trim();
    }

    @Override
    public void setListingChargingPoliciesTV(String text) {
        ((TextView)findViewById(R.id.added_charging_policies)).setText(text);
    }

    @Override
    public void setListingServicesTV(String text) {
        ((TextView)findViewById(R.id.added_listing_services)).setText(text);
    }
}
