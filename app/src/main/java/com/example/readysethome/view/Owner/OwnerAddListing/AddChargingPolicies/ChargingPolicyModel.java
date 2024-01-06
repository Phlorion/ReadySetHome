package com.example.readysethome.view.Owner.OwnerAddListing.AddChargingPolicies;

import java.io.Serializable;

public class ChargingPolicyModel implements Serializable {
    String start_index;
    String end_index;
    String desc;
    String price_diff;

    public ChargingPolicyModel(String start_index, String end_index, String desc, String price_diff) {
        this.start_index = start_index;
        this.end_index = end_index;
        this.desc = desc;
        this.price_diff = price_diff;
    }

    public String getStart_index() {
        return start_index;
    }

    public String getEnd_index() {
        return end_index;
    }

    public String getDesc() {
        return desc;
    }

    public String getPrice_diff() {
        return price_diff;
    }
}
