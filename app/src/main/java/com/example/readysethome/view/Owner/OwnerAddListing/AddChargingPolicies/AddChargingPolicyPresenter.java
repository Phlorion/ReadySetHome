package com.example.readysethome.view.Owner.OwnerAddListing.AddChargingPolicies;

import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readysethome.model.ChargingPolicy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AddChargingPolicyPresenter {
    AddChargingPolicyView view;
    ArrayList<ChargingPolicyModel> chargingPolicyModels;
    ArrayList<ChargingPolicy> chargingPolicies;

    /**
     * 'Ελεγχος αν το στοιχείο είναι κενό.
     * @param str Το στοιχείο
     * @return Ένα bool για το αν το στοιχείο είναι κενό ή όχι
     */
    private boolean isNull(String str) {
        return str.equals("");
    }

    public AddChargingPolicyPresenter(AddChargingPolicyView view) {
        this.view = view;

        chargingPolicies = new ArrayList<>();
        chargingPolicyModels = new ArrayList<>();
    }


    public ArrayList<ChargingPolicyModel> addChargingPolicy(String start_ind, String end_ind, String price_diff, String desc) {
        // check if null strings
        if (isNull(start_ind) || isNull(end_ind) || isNull(price_diff)) {
            view.showMessage("Εισάγετε όλα τα δεδομένα");
            return null;
        }
        // start_ind or end_ind don't match the string format
        else if (!start_ind.matches("\\d{2}-\\d{2}-\\d{4}$") || !end_ind.matches("\\d{2}-\\d{2}-\\d{4}")) {
            view.showMessage("Dates should be: dd-MM-yyyy");
            return null;
        }

        // price dif not double
        try {
            Double.parseDouble(price_diff);
        } catch (NumberFormatException e) {
            view.showMessage("Price dif must be a number");
            return null;
        }

        // invalid dates
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date start; Date end;
        try {
            start = formatter.parse(start_ind);
            end = formatter.parse(end_ind);
        } catch (ParseException e) {
            return null;
        }

        ChargingPolicy chargingPolicy = new ChargingPolicy(start, end, desc, Double.parseDouble(price_diff));
        chargingPolicies.add(chargingPolicy);

        chargingPolicyModels.add(new ChargingPolicyModel(start_ind, end_ind, desc, price_diff + "€"));
        return chargingPolicyModels;
    }

    public ArrayList<ChargingPolicy> getChargingPolicies() {
        return chargingPolicies;
    }

    public ArrayList<ChargingPolicyModel> getChargingPolicyModels() {
        return chargingPolicyModels;
    }

    public void setChargingPolicies(ArrayList<ChargingPolicy> chargingPolicies) {
        this.chargingPolicies = chargingPolicies;
    }

    public void setChargingPoliciesModels() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        for (ChargingPolicy c_p : chargingPolicies) {
            chargingPolicyModels.add(new ChargingPolicyModel(formatter.format(c_p.getStart_index()), formatter.format(c_p.getEnd_index()), c_p.getDescription(), Double.toString(c_p.getPrice_diff()) + "€"));
        }
    }
}
