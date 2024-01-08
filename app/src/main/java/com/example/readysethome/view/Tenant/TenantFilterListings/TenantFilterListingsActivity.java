package com.example.readysethome.view.Tenant.TenantFilterListings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.readysethome.R;
import com.google.android.material.slider.Slider;

public class TenantFilterListingsActivity extends AppCompatActivity implements TenantFilterListingsView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant_home_filter_listings);

        final TenantFilterListingsPresenter presenter = new TenantFilterListingsPresenter(TenantFilterListingsActivity.this);

        Intent intent = getIntent();
        FilterHolder read = (FilterHolder) intent.getSerializableExtra("APPLIED_FILTERS");
        if (read != null) {
            presenter.readFilters(read);
        }

        // cancel
        findViewById(R.id.filters_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage("Cancelling");
                finish();
            }
        });

        // save
        findViewById(R.id.filters_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FilterHolder filters = presenter.addFilters();
                if (filters != null) {
                    int resultCode = 10;
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("FILTERS", filters);
                    setResult(resultCode, resultIntent);
                    finish();
                }
            }
        });
    }

    @Override
    public boolean getAth() {
        return ((CheckBox)findViewById(R.id.checkBox_ath)).isChecked();
    }

    @Override
    public boolean getThes() {
        return ((CheckBox)findViewById(R.id.checkBox_thessal)).isChecked();
    }

    @Override
    public boolean getPatra() {
        return ((CheckBox)findViewById(R.id.checkBox_patra)).isChecked();
    }

    @Override
    public boolean getHrakleio() {
        return ((CheckBox)findViewById(R.id.checkBox_hrakleio)).isChecked();
    }

    @Override
    public boolean getIwan() {
        return ((CheckBox)findViewById(R.id.checkBox_iwan)).isChecked();
    }

    @Override
    public boolean getVolos() {
        return ((CheckBox)findViewById(R.id.checkBox_volos)).isChecked();
    }

    @Override
    public boolean getSyros() {
        return ((CheckBox)findViewById(R.id.checkBox_syros)).isChecked();
    }

    @Override
    public boolean getNafpion() {
        return ((CheckBox)findViewById(R.id.checkBox_nafp)).isChecked();
    }

    @Override
    public void setAth(boolean isAth) {
        ((CheckBox)findViewById(R.id.checkBox_ath)).setChecked(isAth);
    }

    @Override
    public void setThes(boolean isThes) {
        ((CheckBox)findViewById(R.id.checkBox_thessal)).setChecked(isThes);
    }

    @Override
    public void setPatra(boolean isPatra) {
        ((CheckBox)findViewById(R.id.checkBox_patra)).setChecked(isPatra);
    }

    @Override
    public void setHrakleio(boolean isHrakleio) {
        ((CheckBox)findViewById(R.id.checkBox_hrakleio)).setChecked(isHrakleio);
    }

    @Override
    public void setIwan(boolean isIwan) {
        ((CheckBox)findViewById(R.id.checkBox_iwan)).setChecked(isIwan);
    }

    @Override
    public void setVolos(boolean isVolos) {
        ((CheckBox)findViewById(R.id.checkBox_volos)).setChecked(isVolos);
    }

    @Override
    public void setSyros(boolean isSyros) {
        ((CheckBox)findViewById(R.id.checkBox_syros)).setChecked(isSyros);
    }

    @Override
    public void setNafplion(boolean isNafplion) {
        ((CheckBox)findViewById(R.id.checkBox_nafp)).setChecked(isNafplion);
    }

    @Override
    public String getWantedPrice() {
        return Float.toString(((Slider)findViewById(R.id.price_slider)).getValue());
    }

    @Override
    public String getWantedCheckIn() {
        return ((EditText)findViewById(R.id.filter_stay_check_in)).getText().toString().trim();
    }

    @Override
    public String getWantedCheckOut() {
        return ((EditText)findViewById(R.id.filter_stay_check_out)).getText().toString().trim();
    }

    @Override
    public void setWantedCheckIn(String checkIn) {
        ((EditText)findViewById(R.id.filter_stay_check_in)).setText(checkIn);
    }

    @Override
    public void setWantedCheckOut(String checkOut) {
        ((EditText)findViewById(R.id.filter_stay_check_in)).setText(checkOut);
    }

    @Override
    public void setWantedPrice(String wantedPrice) {
        ((Slider)findViewById(R.id.price_slider)).setValue(Float.parseFloat(wantedPrice));
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(TenantFilterListingsActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
