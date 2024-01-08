package com.example.readysethome.view.Owner.OwnerAddListing;

import com.example.readysethome.view.Owner.OwnerAddListing.AddChargingPolicies.AddChargingPolicyView;
import com.example.readysethome.view.Owner.OwnerAddListing.AddChargingPolicies.ChargingPolicyModel;

public class OwnerAddChargingPolicyViewStub implements AddChargingPolicyView {
    String message;

    public OwnerAddChargingPolicyViewStub() {
        message = "";
    }

    @Override
    public void showMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
