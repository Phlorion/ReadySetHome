package com.example.readysethome.model;

import java.util.ArrayList;
import java.util.Date;

public class ChargingPolicy {
    private Date start_index;
    private Date end_index;
    private String description;
    private double price_diff;

    public ChargingPolicy(Date start_index, Date end_index, String description, double price_diff) {
        this.start_index = start_index;
        this.end_index = end_index;
        this.description = description;
        this.price_diff = price_diff;
    }

    public Date getStart_index() {
        return start_index;
    }

    public void setStart_index(Date start_index) {
        this.start_index = start_index;
    }

    public Date getEnd_index() {
        return end_index;
    }

    public void setEnd_index(Date end_index) {
        this.end_index = end_index;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice_diff() {
        return price_diff;
    }

    public void setPrice_diff(double price_diff) {
        this.price_diff = price_diff;
    }

    public void addChargingPolicy(Listing listing) {
        ArrayList<ChargingPolicy> listingChargingPolicies = listing.getChargingPolicies();
        for (ChargingPolicy chargingPolicy : listingChargingPolicies) {
            if (chargingPolicy.equals(this)) {
                System.out.println("This charging policy already exists.");
            }
            else if ((chargingPolicy.getStart_index().getTime() >= this.getEnd_index().getTime()) && (chargingPolicy.getEnd_index().getTime() <= this.getStart_index().getTime())) {
                System.out.println("This charging policy overlaps with another one.");
            }
            else {
                listing.addChargingPolicies(this);
            }
        }
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof ChargingPolicy)) return false;
        if (!super.equals(object)) return false;
        ChargingPolicy that = (ChargingPolicy) object;
        return java.lang.Double.compare(that.getPrice_diff(), getPrice_diff()) == 0 && getStart_index().equals(that.getStart_index()) && getEnd_index().equals(that.getEnd_index()) && getDescription().equals(that.getDescription());
    }
}