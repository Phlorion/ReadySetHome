package com.example.readysethome;

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
}
